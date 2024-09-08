package online.jutter.kztlibrary.domain.models.section

data class CreateSection(
    var title: String,
    var subtitle: String,
    var author: String,
    val cover: String,
    var description: String,
    var link: String,
    var schedule: String,
)
