package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.data.models.auth.LoginRequest
import online.jutter.kztlibrary.data.models.auth.RegisterRequest
import online.jutter.kztlibrary.domain.uscaseses.user.GetInfoUseCase
import online.jutter.kztlibrary.domain.uscaseses.user.RegisterNewUserUseCase
import online.jutter.kztlibrary.domain.uscaseses.user.UserLoginUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/auth"],
        produces = ["application/json; charset=utf-8"]
)
class AuthorisationController {

    private val userLoginUseCase = UserLoginUseCase()
    private val registerNewUserUseCase = RegisterNewUserUseCase()
    private val getInfoUseCase = GetInfoUseCase()

    @RequestMapping(
        value = ["login"],
        method = [RequestMethod.POST]
    )
    fun login(
        @RequestBody loginRequest: LoginRequest,
    ) = createWrapperResponse {
        userLoginUseCase(loginRequest.login!!)
    }

    @RequestMapping(
        value = ["register"],
        method = [RequestMethod.POST]
    )
    fun register(
        @RequestBody registerRequest: RegisterRequest,
    ) = createWrapperResponse {
        registerNewUserUseCase(registerRequest)
    }

    @RequestMapping(
            value = ["getInfo"],
            method = [RequestMethod.GET]
    )
    fun getInfo(
            @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getInfoUseCase(TokenManager.getIdFromToken(token))

    }
}