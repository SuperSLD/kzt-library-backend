package online.jutter.kztlibrary.domain.uscaseses.user

import online.jutter.kztlibrary.data.db.repositories.UsersRepository
import online.jutter.kztlibrary.domain.models.UserResponse
import online.jutter.kztlibrary.domain.uscaseses.achivments.GetAllAchivmentsUseCase

class GetInfoUseCase {

    private val getAllAchivmentsUseCase = GetAllAchivmentsUseCase()

    operator fun invoke(id: String): UserResponse {
        val user = UsersRepository.getById(id)!!
        val achivments = getAllAchivmentsUseCase(user.id)
        return UserResponse(
            id = user.id,
            name = user.name,
            lastname = user.lastname,
            midname = user.midname,
            companyRole = user.companyRole,
            coins = user.coins,
            avatar = user.avatar,
            achivements = achivments
        )
    }
}