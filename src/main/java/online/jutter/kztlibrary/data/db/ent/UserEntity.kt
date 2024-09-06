package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    var id: String = ""
    var login: String = ""
    @Column(name = "user_name")
    var name: String = ""
    @Column(name = "user_lastname")
    var lastname: String = ""
    @Column(name = "user_midname")
    var midname: String = ""
    @Column(name = "company_role")
    var companyRole: String = ""
    var coins: Int = 0
    val avatar: String = ""
}