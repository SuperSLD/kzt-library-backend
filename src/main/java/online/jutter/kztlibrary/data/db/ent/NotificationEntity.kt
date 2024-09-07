package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "notifications")
class NotificationEntity {
    @Id
    var id: String = ""
    var title: String = ""
    var text: String = ""
    var type: String = ""
    @Column(name = "event_id")
    var eventId: String? = null
    @Column(name = "book_id")
    var bookId: String? = null
    @Column(name = "user_id")
    var user: String = ""
}