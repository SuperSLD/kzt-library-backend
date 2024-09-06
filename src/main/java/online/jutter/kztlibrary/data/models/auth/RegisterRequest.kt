package online.jutter.kztlibrary.data.models.auth

data class RegisterRequest(
    val login: String,
    val name: String,
    val lastname: String,
    val midname: String,
    val avatarUrl: String?,
)
