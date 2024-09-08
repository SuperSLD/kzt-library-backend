package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sections")
class SectionEntity {
    @Id
    var id: String = ""
    var title: String = ""
    var subtitle: String = ""
    var author: String = ""
    var cover: String = ""
    var description: String = ""
    var url: String = ""
    var schedule: String = ""
}