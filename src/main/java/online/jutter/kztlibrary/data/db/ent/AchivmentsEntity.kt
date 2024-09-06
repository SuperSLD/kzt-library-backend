package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "achivments")
class AchivmentsEntity {
    @Id
    var id: String = ""
    var title: String = ""
    var description: String = ""
    var icon: String = ""
}