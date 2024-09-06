package online.jutter.kztlibrary.data.db

import online.jutter.kztlibrary.common.HibernateUtils
import org.hibernate.Criteria
import org.hibernate.Session
import org.hibernate.criterion.*
import java.io.Serializable
import java.lang.reflect.ParameterizedType


/**
 * Базовый класс репозитория для сущностей.
 * Содержит стандартные методы для взаимодействия с базой.
 *
 * [ENT] сущность hibernate.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
open class BaseRepository<ENT> {

    var persistentClass: Class<ENT>? = null
    init {
        @Suppress("UNCHECKED_CAST")
        this.persistentClass =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<ENT>?
    }

    /**
     * Вставка в базу сущности [ENT].
     */
    open fun insert(entity: ENT) = executeTransaction {
        persist(entity)
    }

    /**
     * получение сущности по ID.
     */
    open fun getById(id: Serializable): ENT? = executeTransaction {
        get(persistentClass!!, id)
    }

    fun Session.getById(id: Serializable): ENT? {
        return this.get(persistentClass!!, id)
    }

    /**
     * Получение всех сущностей.
     */
    open fun getAll(): MutableList<ENT> {
        val result = mutableListOf<ENT>()
        executeTransaction {
            result.addAll(getAll(persistentClass!!))
        }
        return result
    }

    fun Session.getAll(): MutableList<ENT> {
        return getAll(persistentClass!!)
    }

    /**
     * Обновление сущности.
     */
    open fun update(entity: ENT) {
        executeTransaction {
            update(entity)
        }
    }

    /**
     * Удаление сущности.
     */
    open fun remove(entity: ENT) {
        executeTransaction {
            remove(entity)
        }
    }

    /**
     * Удаление сущности по ID.
     */
    open fun removeById(id: Serializable) {
        executeTransaction {
            remove(get(persistentClass, id))
        }
    }

    fun Session.removeById(id: Serializable) {
        remove(get(persistentClass, id))
    }

    /**
     * Получение страницы из базы,
     * с ограниченным количеством полей
     * и пропуском изначальных N записей.
     *
     * @param count количество записей
     * @param skip количество пропущенных записей
     * @param orderBy сортировать записи по
     * @param restrictions условия которым должны удоволетворять записи
     */
    open fun getPage(
        count: Int,
        skip: Int,
        orderBy: String,
        restrictions: List<SimpleExpression> = mutableListOf()
    ) : MutableList<ENT> {
        val result = mutableListOf<ENT>()
        executeTransaction {
            val criteria = createCriteria(persistentClass)
            criteria.setFirstResult(skip)
            criteria.setMaxResults(count)
            criteria.addOrder(Order.desc(orderBy))
            for (restriction in restrictions) {
                criteria.add(restriction)
            }
            result.addAll(criteria.list().toMutableList() as MutableList<ENT>)
        }
        return result
    }

    /**
     * Получение страницы из базы,
     * с ограниченным количеством полей
     * и пропуском изначальных N записей.
     *
     * @param count количество записей
     * @param skip количество пропущенных записей
     * @param orderBy сортировать записи по
     * @param restrictions условия которым должны удоволетворять записи
     */
    fun Session.getPage(
        count: Int,
        skip: Int,
        orderBy: String,
        restrictions: List<SimpleExpression> = mutableListOf()
    ) : MutableList<ENT> {
        val criteria = createCriteria(persistentClass)
        criteria.setFirstResult(skip)
        criteria.setMaxResults(count)
        criteria.addOrder(Order.desc(orderBy))
        for (restriction in restrictions) {
            criteria.add(restriction)
        }
        return criteria.list().toMutableList() as MutableList<ENT>
    }

    /**
     * Выполнение транзакции.
     * Метод упрощает запуск транзакций и
     * избавляет от необходимости обращаться напрямую
     * к [HibernateUtils] для создания сессии и
     * начала транзакции.
     *
     * @param transaction тело транзакции.
     */
    fun <T> executeTransaction(transaction: Session.() -> T) : T? {
        val session = createSession()
        val result: T?
        try {
            session.transaction.begin()
            result = transaction(session)
            session.transaction.commit()
        } catch (ex: Exception) {
            throw ex
        } finally {
            session.close()
        }
        return result
    }

    /**
     * Выполнение транзакции.
     * Метод упрощает запуск транзакций и
     * избавляет от необходимости обращаться напрямую
     * к [HibernateUtils] для создания сессии
     * начала транзакции.
     * С калбеком для обработки ошибок.
     *
     * @param transaction транзакция.
     * @param catch калбэк ошибки при выполнении транзакции.
     */
    fun <T> executeTransaction(transaction: Session.() -> T, catch: (Throwable) -> Unit) : T?{
        val session = createSession()
        var result: T? = null
        try {
            session.transaction.begin()
            result = transaction(session)
            session.transaction.commit()
        } catch (ex: Exception) {
            catch(ex)
        } finally {
            session.close()
        }
        return result
    }

    /**
     * Создание сессии с повторением в случае провала.
     */
    private fun createSession(): Session = try {
        HibernateUtils.getSessionFactory().openSession()
    } catch (ex: Exception) {
        ex.printStackTrace()
        throw error("Не удалось установить ссоединение с БД")
    }
}

infix fun String.eq(value: Any) = Restrictions.eq(this, value)!!
infix fun Criterion.and(value: Criterion) = Restrictions.and(this, value)!!
infix fun Criterion.or(value: Criterion) = Restrictions.or(this, value)!!

/**
 * Удаление всех элементов из базы
 * по сущности
 */
fun Session.removeAllInstances(clazz: Class<*>) {
    val hql = String.format("DELETE FROM %s", clazz.simpleName)
    val query = session.createQuery(hql)
    query.executeUpdate()
}

/**
 * Поиск сушности по базе через имя поля и
 * згачение поля.
 * @param name итя столбца.
 * @param value предпологаемое значение.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> Session.search(name: String, value: String): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    query.add(Restrictions.like(name, value, MatchMode.ANYWHERE))
    return query.list() as MutableList<T>
}

/**
 * Список сущностей с наличием определенного
 * значения.
 * (SELECT * FROM table WHERE name = value;)
 * @param name итя столбца.
 * @param value значение.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> Session.getQuery(name: String, value: Serializable): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.add(Restrictions.eq(name, value)).list() as MutableList<T>
}

/**
 * Список сущностей с наличием определенного
 * значения.
 * (SELECT * FROM table WHERE name = value;)
 * @param restrictions выражения для филтирации.
 */
inline fun <reified T> Session.getQuery(vararg restrictions: Criterion): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    for (restriction in restrictions) {
        query.add(restriction)
    }
    @Suppress("UNCHECKED_CAST")
    return query.list() as MutableList<T>
}

/**
 * Список всех сущностей.
 * (SELECT * FROM table;)
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> Session.getAll(): MutableList<T> {
    val query = session.createCriteria(T::class.java)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.list() as MutableList<T>
}

/**
 * Список всех сущностей.
 * (SELECT * FROM table;)
 */
@Suppress("UNCHECKED_CAST")
fun <T> Session.getAll(clazz: Class<*>): MutableList<T> {
    val query = session.createCriteria(clazz)
    query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
    return query.list() as MutableList<T>
}