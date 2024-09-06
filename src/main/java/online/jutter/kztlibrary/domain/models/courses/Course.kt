package online.jutter.kztlibrary.domain.models.courses

data class Course(
    val tag: String,
    val title: String,
    val isNew: Boolean,
    val isImportant: Boolean,
    val isChecklist: Boolean,
    val description: String,
    val lessons: List<Lesson>,
    val checklistItems: List<ChecklistItem>,
)

data class Lesson(
    val title: String,
    val description: String,
    val pages: List<LessonPage>,
    val number: Int
)

data class LessonPage(
    val description: String,
    val image: String,
    val isAnswer: Boolean,
    val correctAnswer: String,
    val answers: List<LessonPageAnswer>,
    val number: Int
)

data class LessonPageAnswer(
    val title: String? = null,
)

data class ChecklistItem(
    val title: String,
    val description: String,
    val number: Int
)

data class UserChecklistItem(
    val checklistItemId: String,
    val userId: String,
)

data class UserFinishedLesson(
    val lessonId: String,
    val userId: String,
)
