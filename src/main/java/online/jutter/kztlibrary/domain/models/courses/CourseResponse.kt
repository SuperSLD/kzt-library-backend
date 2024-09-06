package online.jutter.kztlibrary.domain.models.courses

data class CourseResponse(
    val id: String,
    val tag: String,
    val title: String,
    val isNew: Boolean,
    val isImportant: Boolean,
    val isChecklist: Boolean,
    val description: String,
    val lessons: List<LessonResponse>,
    val checklistItems: List<ChecklistItemResponse>,
)

data class LessonResponse(
    val id: String,
    val title: String,
    val description: String,
    val pages: List<LessonPageResponse>,
    val number: Int,
    val finished: Boolean,
)

data class LessonPageResponse(
    val id: String,
    val description: String,
    val image: String,
    val isAnswer: Boolean,
    val correctAnswer: String,
    val correctAnswerId: String?,
    val answers: List<LessonPageAnswerResponse>,
    val number: Int
)

data class LessonPageAnswerResponse(
    val id: String,
    val title: String? = null,
)

data class ChecklistItemResponse(
    val id: String,
    val title: String,
    val description: String,
    val number: Int,
    val checked: Boolean,
)
