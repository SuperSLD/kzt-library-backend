package online.jutter.kztlibrary.domain.models.books

data class Book(
    val author: String,
    val title: String,
    val description: String,
    val rating: Float,
    val cover: String,
)
