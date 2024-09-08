package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.books.Book
import online.jutter.kztlibrary.domain.models.section.CreateSection
import online.jutter.kztlibrary.domain.uscaseses.books.*
import online.jutter.kztlibrary.domain.uscaseses.sections.GetAllSectionsUseCase
import online.jutter.kztlibrary.domain.uscaseses.sections.SetAllSectionsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/sections"],
        produces = ["application/json; charset=utf-8"]
)
class SectionsController {

    private val setAllSectionsUseCase = SetAllSectionsUseCase()
    private val getAllSectionsUseCase = GetAllSectionsUseCase()

    @RequestMapping(
        value = ["get"],
        method = [RequestMethod.GET]
    )
    fun get() = createWrapperResponse {
        getAllSectionsUseCase()
    }

    @RequestMapping(
            value = ["set"],
            method = [RequestMethod.POST]
    )
    fun set(
        @RequestBody sections: List<CreateSection>,
    ) = createEmptyWrapperResponse {
        setAllSectionsUseCase(sections)
    }
}