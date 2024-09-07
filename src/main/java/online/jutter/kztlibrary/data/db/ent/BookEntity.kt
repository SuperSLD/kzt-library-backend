package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "books")
class BookEntity {
    @Id
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var rating: Float = 0F
    var cover: String = ""
    var renewal: Boolean = false
    var author: String = ""
}