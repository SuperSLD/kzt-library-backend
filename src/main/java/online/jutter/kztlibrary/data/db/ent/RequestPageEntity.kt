package online.jutter.kztlibrary.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "request_pages")
class RequestPageEntity {

    @Id
    var id: String = ""
    @Column(name = "page_number")
    var number: Int = 0

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "page_id")
    var lines: Set<RequestPageLineEntity> = setOf()

    @Column(name = "request_id")
    var requestId: String = ""
}