package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createWrapperResponse

import online.jutter.kztlibrary.domain.uscaseses.news.GetCentralUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/news/central"],
        produces = ["application/json; charset=utf-8"]
)
class CentralController {

    private val getCentralUseCase = GetCentralUseCase()

    @RequestMapping(
        value = ["getCentral"],
        method = [RequestMethod.GET]
    )
    fun get(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getCentralUseCase(TokenManager.getIdFromToken(token))
    }


}