package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "checklist_items")
class ChecklistItemEntity {

    @Id
    var id: String = ""
    var title: String = ""
    var description: String = ""

    @Column(name = "course_id")
    var courseId: String = ""
    var number: Int = 0

    fun getItemNumber() = number
}