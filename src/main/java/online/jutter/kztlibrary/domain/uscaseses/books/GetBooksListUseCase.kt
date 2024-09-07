package online.jutter.kztlibrary.domain.uscaseses.books

import online.jutter.kztlibrary.data.db.repositories.BooksRepository

class GetBooksListUseCase {

    operator fun invoke(userId: String) = BooksRepository.getAllUserBooks(userId)
}