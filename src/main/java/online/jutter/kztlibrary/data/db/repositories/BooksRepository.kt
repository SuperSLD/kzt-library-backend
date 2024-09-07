package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.BaseRepository
import online.jutter.kztlibrary.data.db.ent.BookEntity
import online.jutter.kztlibrary.data.db.ent.UserBookEntity
import online.jutter.kztlibrary.data.db.eq
import online.jutter.kztlibrary.data.db.getQuery
import online.jutter.kztlibrary.data.db.removeAllInstances

object BooksRepository : BaseRepository<BookEntity>() {

    fun setAll(books: List<BookEntity>) = executeTransaction {
        removeAllInstances(UserBookEntity::class.java)
        removeAllInstances(BookEntity::class.java)

        for (item in books) {
            persist(item)
        }
    }

    fun getAllUserBooks(userId: String) = executeTransaction {
        getQuery<UserBookEntity>("user" eq userId).map {
            getById(it.book)
        }
    }
}