package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "documents")
class DocumentEntity {
    @Id
    var id: String = ""
    @Column(name = "user_id")
    var user: String = ""
    var title: String = ""
    var deadline: String = ""
}