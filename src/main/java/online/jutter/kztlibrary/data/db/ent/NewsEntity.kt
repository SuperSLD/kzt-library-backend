package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "news")
class NewsEntity {
    @Id
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var image: String = ""
}