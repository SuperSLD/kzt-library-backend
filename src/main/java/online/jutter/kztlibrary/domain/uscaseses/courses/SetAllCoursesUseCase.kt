package online.jutter.kztlibrary.domain.uscaseses.courses

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.CourseEntity
import online.jutter.kztlibrary.data.db.ent.LessonEntity
import online.jutter.kztlibrary.data.db.ent.LessonPageEntity
import online.jutter.kztlibrary.data.db.ent.LessonPageAnswerEntity
import online.jutter.kztlibrary.data.db.ent.ChecklistItemEntity
import online.jutter.kztlibrary.data.db.repositories.CoursesRepository
import online.jutter.kztlibrary.domain.models.courses.Course
import online.jutter.kztlibrary.domain.models.courses.Lesson
import online.jutter.kztlibrary.domain.models.courses.LessonPage
import online.jutter.kztlibrary.domain.models.courses.LessonPageAnswer
import online.jutter.kztlibrary.domain.models.courses.ChecklistItem

class SetAllCoursesUseCase {

    operator fun invoke(courses: List<Course>) {
        CoursesRepository.setAllCourses(courses.map { mapToEnt(it) })
    }

    fun mapToEnt(course: Course) =
        CourseEntity().apply {
            id = getUUID()
            tag = course.tag
            title = course.title
            isNew = course.isNew
            isImportant = course.isImportant
            isChecklist = course.isChecklist
            description = course.description
            lessons = course.lessons.map { mapToEnt(it, id) }.toSet()
            checklistItems = course.checklistItems.map { mapToEnt(it, id) }.toSet()
        }

    fun mapToEnt(lesson: Lesson, courseId: String) =
        LessonEntity().apply {
            id = getUUID()
            this.courseId = courseId
            title = lesson.title
            description = lesson.description
            pages = lesson.pages.map { mapToEnt(it, id) }.toSet()
            number = lesson.number
        }

    fun mapToEnt(page: LessonPage, lessonId: String) =
        LessonPageEntity().apply {
            id = getUUID()
            this.lessonId = lessonId
            description = page.description
            image = page.image
            isAnswer = page.isAnswer
            correctAnswer = page.correctAnswer
            answers = page.answers.map { mapToEnt(it, id) }.toSet()
            number = page.number
        }

    fun mapToEnt(answer: LessonPageAnswer, lessonPageId: String) =
        LessonPageAnswerEntity().apply {
            id = getUUID()
            this.lessonPageId = lessonPageId
            title = answer.title!!
        }

    fun mapToEnt(checklist_item: ChecklistItem, courseId: String) =
        ChecklistItemEntity().apply {
            id = getUUID()
            this.courseId = courseId
            title = checklist_item.title
            description = checklist_item.description
            number = checklist_item.number
        }
}