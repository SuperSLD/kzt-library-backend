package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_books")
class UserBookEntity {
    @Id
    var id: String = ""
    @Column(name = "user_id")
    var user: String = ""
    @Column(name = "book_id")
    var book: String = ""
    @Column(name = "return_date")
    var returnDate: String = ""
    @Column(name = "is_return")
    var isReturn: Boolean = false
}