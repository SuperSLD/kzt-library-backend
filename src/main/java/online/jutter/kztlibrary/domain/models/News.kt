package online.jutter.kztlibrary.domain.models

data class News(
    val title: String,
    val description: String,
    val image: String,
    val type: String,
    val date: String?,
)
