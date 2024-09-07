package online.jutter.kztlibrary.domain.uscaseses.books

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.common.ext.toCalendar
import online.jutter.kztlibrary.common.ext.toDateString
import online.jutter.kztlibrary.data.db.and
import online.jutter.kztlibrary.data.db.ent.UserBookEntity
import online.jutter.kztlibrary.data.db.eq
import online.jutter.kztlibrary.data.db.getQuery
import online.jutter.kztlibrary.data.db.repositories.BooksRepository
import java.util.*

class AddBookToUserUseCase {

    operator fun invoke(userId: String, bookId: String) {
        BooksRepository.executeTransaction {
            val userBook = getQuery<UserBookEntity>(
                ("book" eq bookId) and ("user" eq userId)
            ).firstOrNull()
            if (userBook != null) throw error("Книга уже у вас")
            val newBook = UserBookEntity().apply {
                id = getUUID()
                user = userId
                book = bookId
                count = 2
                returnDate = Calendar.getInstance().apply { this.add(Calendar.DAY_OF_WEEK, 45) }.toDateString()
            }
            persist(newBook)
        }
    }
}