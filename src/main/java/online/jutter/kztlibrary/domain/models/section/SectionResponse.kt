package online.jutter.kztlibrary.domain.models.section

data class SectionResponse(
    var title: String,
    var subtitle: String,
    var author: String,
    var authorAvatar: String,
    val cover: String,
    var description: String,
    var link: String,
    var schedule: String,
)
