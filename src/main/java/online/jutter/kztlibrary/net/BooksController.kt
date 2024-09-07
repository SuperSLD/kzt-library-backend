package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.Achivment
import online.jutter.kztlibrary.domain.models.books.Book
import online.jutter.kztlibrary.domain.uscaseses.achivments.GetAllAchivmentsUseCase
import online.jutter.kztlibrary.domain.uscaseses.achivments.SetAllAchivmentsUseCase
import online.jutter.kztlibrary.domain.uscaseses.books.GetAllBooksUseCase
import online.jutter.kztlibrary.domain.uscaseses.books.SetAllBooksUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/books"],
        produces = ["application/json; charset=utf-8"]
)
class BooksController {

    private val setAllBooksUseCase = SetAllBooksUseCase()
    private val getAllBooksUseCase = GetAllBooksUseCase()

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
}