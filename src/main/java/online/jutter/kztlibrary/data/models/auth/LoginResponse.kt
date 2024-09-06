package online.jutter.kztlibrary.data.models.auth

data class LoginResponse(
    val token: String?,
    val isRegistered: Boolean,
)