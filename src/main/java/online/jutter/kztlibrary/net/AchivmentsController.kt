package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.Achivment
import online.jutter.kztlibrary.domain.uscaseses.achivments.GetAllAchivmentsUseCase
import online.jutter.kztlibrary.domain.uscaseses.achivments.SetAllAchivmentsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/achivments"],
        produces = ["application/json; charset=utf-8"]
)
class AchivmentsController {

    private val setAllAchivmentsUseCase = SetAllAchivmentsUseCase()
    private val getAllAchivmentsUseCase = GetAllAchivmentsUseCase()

    @RequestMapping(
        value = ["get"],
        method = [RequestMethod.GET]
    )
    fun get(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getAllAchivmentsUseCase(TokenManager.getIdFromToken(token))
    }

    @RequestMapping(
            value = ["set"],
            method = [RequestMethod.POST]
    )
    fun set(
        @RequestHeader("Authorization") token: String,
        @RequestBody achivments: List<Achivment>,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        setAllAchivmentsUseCase(achivments)
    }
}