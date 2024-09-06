package online.jutter.kztlibrary.data.models

/**
 * Общая модель ответа сервера.
 * [T] тип основной части ответа.
 */
data class DataWrapper<T> (
    var success: Boolean = false,
    var message: String = "",
    var data: T? = null
)
