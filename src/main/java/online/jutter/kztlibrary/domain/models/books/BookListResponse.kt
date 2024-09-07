package online.jutter.kztlibrary.domain.models.books

data class BookListResponse(
    val myBook: List<BookResponse>,
    val rec: List<BookResponse>,
    val new: List<BookResponse>,
)
