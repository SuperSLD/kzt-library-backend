package online.jutter.kztlibrary.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "requests")
class RequestEntity {

    @Id
    var id: String = ""
    var title: String = ""
    @Column(name = "icon_name")
    var icon: String = ""

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "request_id")
    var pages: Set<RequestPageEntity> = setOf()
}