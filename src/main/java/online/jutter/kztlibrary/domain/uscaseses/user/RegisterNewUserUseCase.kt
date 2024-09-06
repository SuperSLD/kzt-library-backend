package online.jutter.kztlibrary.domain.uscaseses.user

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.UserEntity
import online.jutter.kztlibrary.data.db.repositories.UsersRepository
import online.jutter.kztlibrary.data.models.auth.LoginResponse
import online.jutter.kztlibrary.data.models.auth.RegisterRequest

class RegisterNewUserUseCase {

    operator fun invoke(registerRequest: RegisterRequest): LoginResponse {
        val user = UsersRepository.getByLogin(registerRequest.login)
        if (user != null) throw error("Пользователь уже зарегистрирован")
        val userEntity = UserEntity().apply {
            this.id = getUUID()
            this.login = registerRequest.login
            this.name = registerRequest.name
            this.lastname = registerRequest.lastname
            this.midname = registerRequest.midname
            this.avatar = registerRequest.avatarUrl
            this.coins = 125
        }
        UsersRepository.insert(userEntity)
        return LoginResponse(TokenManager.getToken(userEntity), true)
    }
}