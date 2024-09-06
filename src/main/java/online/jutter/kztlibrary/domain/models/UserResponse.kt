package online.jutter.kztlibrary.domain.models

data class UserResponse(
    val id: String,
    val name: String,
    val lastname: String,
    val midname: String,
    val companyRole: String?,
    val coins: Int,
    val avatar: String?,
    val achivements: List<AchivmentResponse>,
)