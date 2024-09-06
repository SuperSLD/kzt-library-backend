package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_achivments")
class UserAchivmentEntity {
    @Id
    var id: String = ""
    @Column(name = "user_id")
    var user: String = ""
    @Column(name = "achivment_id")
    var achivment: String = ""
}