package online.jutter.kztlibrary.domain.uscaseses.achivments

import online.jutter.kztlibrary.data.db.repositories.AchivmentsRepository

class GiveUserAchivmentUseCase {

    operator fun invoke(icon: String, userId: String) {
        AchivmentsRepository.giveAchivment(icon, userId)
    }
}