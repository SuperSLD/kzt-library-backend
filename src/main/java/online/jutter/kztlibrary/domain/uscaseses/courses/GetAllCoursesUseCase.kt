package online.jutter.kztlibrary.domain.uscaseses.courses

import online.jutter.kztlibrary.data.db.repositories.CoursesRepository
import online.jutter.kztlibrary.domain.models.courses.*

class GetAllCoursesUseCase {

    operator fun invoke(userId: String) = CoursesRepository.getAll().map {
        CourseResponse(
            id = it.id,
            tag = it.tag,
            title = it.title,
            description = it.description,
            isImportant = it.isImportant,
            isChecklist = it.isChecklist,
            isNew = it.isNew,
            checklistItems = it.checklistItems.map { checkItem ->
                ChecklistItemResponse(
                    id = checkItem.id,
                    title = checkItem.title,
                    description = checkItem.description,
                    number = checkItem.getItemNumber(),
                    checked = CoursesRepository.isChecked(userId, checkItem.id)!!
                )
            },
            lessons = it.lessons.map { lesson ->
                LessonResponse(
                    id = lesson.id,
                    title = lesson.title,
                    description = lesson.description,
                    number = lesson.getItemNumber(),
                    finished = CoursesRepository.isFinished(userId, lesson.id)!!,
                    pages = lesson.pages.map { page ->
                        LessonPageResponse(
                            id = page.id,
                            description = page.description,
                            image = page.image,
                            isAnswer = page.isAnswer,
                            correctAnswer = page.correctAnswer,
                            correctAnswerId = page.getCorrectAnswerId(),
                            number = page.getItemNumber(),
                            answers = page.answers.map { answer ->
                                LessonPageAnswerResponse(
                                    id = answer.id,
                                    title = answer.title,
                                )
                            }
                        )
                    }
                )
            }
        )
    }
}
