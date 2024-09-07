package online.jutter.kztlibrary.domain.uscaseses.books

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.BookEntity
import online.jutter.kztlibrary.data.db.repositories.BooksRepository
import online.jutter.kztlibrary.domain.models.books.Book

class SetAllBooksUseCase {

    operator fun invoke(books: List<Book>) {
        BooksRepository.setAll(
            books.map { book ->
                BookEntity().apply {
                    id = getUUID()
                    title = book.title
                    description = book.description
                    rating = book.rating
                    cover = book.cover
                    author = book.author
                }
            }
        )
    }
}