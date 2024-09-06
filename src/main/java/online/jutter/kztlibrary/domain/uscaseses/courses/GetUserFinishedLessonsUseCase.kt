package online.jutter.kztlibrary.domain.uscaseses.courses

import online.jutter.kztlibrary.data.db.repositories.CoursesRepository
import online.jutter.kztlibrary.domain.uscaseses.user.AddUserCoinsUseCase

class GetUserFinishedLessonsUseCase {

    private val addUserCoinsUseCase = AddUserCoinsUseCase()

    operator fun invoke(userId: String, finishedItemId: String) {
        addUserCoinsUseCase(userId, 50)
        CoursesRepository.finished(userId, finishedItemId)
    }
}