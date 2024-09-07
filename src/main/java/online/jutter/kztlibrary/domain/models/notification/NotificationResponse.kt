package online.jutter.kztlibrary.domain.models.notification

import online.jutter.kztlibrary.data.db.ent.NewsEntity

data class NotificationResponse(
    val title: String,
    val text: String,
    val type: String,
    val event: NewsEntity? = null,
)
