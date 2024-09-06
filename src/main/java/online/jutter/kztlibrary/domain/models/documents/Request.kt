package online.jutter.kztlibrary.domain.models.documents

data class Request(
    val title: String,
    val icon: String,
    val pages: List<RequestPage>,
)

data class RequestPage(
    val number: Int,
    val lines: List<RequestPageLine>,
)

data class RequestPageLine(
    val type: String,
    val content: String,
    val required: Boolean,
    val number: Int,
)