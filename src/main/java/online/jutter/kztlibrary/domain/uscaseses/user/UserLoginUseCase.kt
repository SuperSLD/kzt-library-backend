package online.jutter.kztlibrary.domain.uscaseses.user

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.data.db.repositories.UsersRepository
import online.jutter.kztlibrary.data.models.auth.LoginResponse
import online.jutter.kztlibrary.domain.uscaseses.achivments.GiveUserAchivmentUseCase

class UserLoginUseCase {

    private val giveUserAchivmentUseCase = GiveUserAchivmentUseCase()

    operator fun invoke(login: String): LoginResponse {
        val user = UsersRepository.getByLogin(login) ?: return LoginResponse(null, false)
        val token = TokenManager.getToken(user)
        giveUserAchivmentUseCase("authorisation", user.id)
        return LoginResponse(token, true)
    }
}