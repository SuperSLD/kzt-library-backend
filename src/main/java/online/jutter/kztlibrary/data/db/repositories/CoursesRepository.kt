package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.*
import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.*

object CoursesRepository : BaseRepository<CourseEntity>() {

    fun setAllCourses(courses: List<CourseEntity>) = executeTransaction {
        removeAllInstances(CourseEntity::class.java)
        removeAllInstances(LessonEntity::class.java)
        removeAllInstances(LessonPageEntity::class.java)
        removeAllInstances(LessonPageAnswerEntity::class.java)
        removeAllInstances(ChecklistItemEntity::class.java)
        removeAllInstances(UserChecklistItemEntity::class.java)

        for (item in courses) {
            persist(item)
        }
    }

    fun check(userId: String, checkId: String) = executeTransaction {
        val checkUserItem = getQuery<UserChecklistItemEntity>(
            ("userId" eq userId) and ("checklistItemId" eq checkId)
        ).firstOrNull()
        if (checkUserItem == null) {
            persist(
                UserChecklistItemEntity().apply {
                    id = getUUID()
                    this.userId = userId
                    checklistItemId = checkId
                }
            )
        }
    }

    fun finished(userId: String, finishedId: String) = executeTransaction {
        val finishedUserItem = getQuery<UserFinishedLessonEntity>(
            ("userId" eq userId) and ("lessonId" eq finishedId)
        ).firstOrNull()
        if (finishedUserItem == null) {
            persist(
                UserFinishedLessonEntity().apply {
                    id = getUUID()
                    this.userId = userId
                    lessonId = finishedId
                }
            )
        }
    }

    fun isFinished(userId: String, lessonId: String) = executeTransaction {
        getQuery<UserFinishedLessonEntity>(
            ("userId" eq userId) and ("lessonId" eq lessonId)
        ).firstOrNull() != null
    }

    fun isChecked(userId: String, itemId: String) = executeTransaction {
        getQuery<UserChecklistItemEntity>(
            ("userId" eq userId) and ("checklistItemId" eq itemId)
        ).firstOrNull() != null
    }
}