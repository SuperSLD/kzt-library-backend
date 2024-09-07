package online.jutter.kztlibrary.domain.uscaseses.books

import online.jutter.kztlibrary.data.db.repositories.BooksRepository

class GetAllBooksUseCase {

    operator fun invoke() = BooksRepository.getAll()
}