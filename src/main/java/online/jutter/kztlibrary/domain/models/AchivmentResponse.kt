package online.jutter.kztlibrary.domain.models

data class AchivmentResponse(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isDone: Boolean,
)
