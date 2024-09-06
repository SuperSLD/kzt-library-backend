package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "request_page_lines")
class RequestPageLineEntity {

    @Id
    var id: String = ""
    @Column(name = "line_type")
    var type: String = ""
    @Column(name = "line_content")
    var content: String = ""
    @Column(name = "requred")
    var required: Boolean = false
    @Column(name = "line_number")
    var number: Int = 0

    @Column(name = "page_id")
    var pageId: String = ""
}