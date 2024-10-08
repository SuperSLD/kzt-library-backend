package online.jutter.kztlibrary.domain.models

import online.jutter.kztlibrary.domain.models.notification.NotificationResponse

data class UserResponse(
    val id: String,
    val name: String,
    val lastname: String,
    val midname: String,
    val companyRole: String?,
    val coins: Int,
    val avatar: String?,
    val achivements: List<AchivmentResponse>,
    val notifications: List<NotificationResponse>,
)