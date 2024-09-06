package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.courses.Course
import online.jutter.kztlibrary.domain.uscaseses.courses.CheckUserChecklistItemUseCase
import online.jutter.kztlibrary.domain.uscaseses.courses.GetUserFinishedLessonsUseCase
import online.jutter.kztlibrary.domain.uscaseses.courses.GetAllCoursesUseCase
import online.jutter.kztlibrary.domain.uscaseses.courses.SetAllCoursesUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    value = ["api/courses"],
    produces = ["application/json; charset=utf-8"]
)
class CoursesController {

    private val setAllCoursesUseCase = SetAllCoursesUseCase()
    private val getAllCoursesUseCase = GetAllCoursesUseCase()
    private val checkUserChecklistItemUseCase = CheckUserChecklistItemUseCase()
    private val getUserFinishedLessonsUseCase = GetUserFinishedLessonsUseCase()

    @RequestMapping(
        value = ["get"],
        method = [RequestMethod.GET]
    )
    fun get(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getAllCoursesUseCase(TokenManager.getIdFromToken(token))
    }

    @RequestMapping(
        value = ["set"],
        method = [RequestMethod.POST]
    )
    fun set(
        @RequestHeader("Authorization") token: String,
        @RequestBody courses: List<Course>,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        setAllCoursesUseCase(courses)
    }

    @RequestMapping(
        value = ["check/{id}"],
        method = [RequestMethod.GET]
    )
    fun check(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: String,
    ) = createEmptyWrapperResponse {
        checkUserChecklistItemUseCase(TokenManager.getIdFromToken(token), id)
    }

    @RequestMapping(
        value = ["finished/{id}"],
        method = [RequestMethod.GET]
    )
    fun finished(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: String,
    ) = createEmptyWrapperResponse {
        getUserFinishedLessonsUseCase(TokenManager.getIdFromToken(token), id)
    }
}