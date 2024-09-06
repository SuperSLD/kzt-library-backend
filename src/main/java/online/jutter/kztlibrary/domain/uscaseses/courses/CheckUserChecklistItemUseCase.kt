package online.jutter.kztlibrary.domain.uscaseses.courses

import online.jutter.kztlibrary.data.db.repositories.CoursesRepository
import online.jutter.kztlibrary.domain.uscaseses.user.AddUserCoinsUseCase

class CheckUserChecklistItemUseCase {

    private val addUserCoinsUseCase = AddUserCoinsUseCase()

    operator fun invoke(userId: String, checkItemId: String) {
        addUserCoinsUseCase(userId, 25)
        CoursesRepository.check(userId, checkItemId)
    }
}