package online.jutter.kztlibrary.domain.models.notification

data class Notification(
    val title: String,
    val text: String,
    val type: String,
    val user: String,
    val event: String? = null,
    val boock: String? = null,
)

