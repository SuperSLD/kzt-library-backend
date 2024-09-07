package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.books.Book
import online.jutter.kztlibrary.domain.uscaseses.books.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/books"],
        produces = ["application/json; charset=utf-8"]
)
class BooksController {

    private val setAllBooksUseCase = SetAllBooksUseCase()
    private val getAllBooksUseCase = GetAllBooksUseCase()
    private val getBooksListUseCase = GetBooksListUseCase()
    private val addBookToUserUseCase = AddBookToUserUseCase()
    private val renewBookUseCase = RenewBookUseCase()

    @RequestMapping(
        value = ["get"],
        method = [RequestMethod.GET]
    )
    fun get() = createWrapperResponse {
        getAllBooksUseCase()
    }

    @RequestMapping(
            value = ["set"],
            method = [RequestMethod.POST]
    )
    fun set(
        @RequestBody book: List<Book>,
    ) = createEmptyWrapperResponse {
        setAllBooksUseCase(book)
    }

    @RequestMapping(
        value = ["list"],
        method = [RequestMethod.GET]
    )
    fun list(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getBooksListUseCase(TokenManager.getIdFromToken(token))
    }

    @RequestMapping(
        value = ["add/{id}"],
        method = [RequestMethod.GET]
    )
    fun add(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: String,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        addBookToUserUseCase(TokenManager.getIdFromToken(token), id)
    }

    @RequestMapping(
        value = ["renew/{id}"],
        method = [RequestMethod.GET]
    )
    fun renew(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: String,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        renewBookUseCase(TokenManager.getIdFromToken(token), id)
    }
}