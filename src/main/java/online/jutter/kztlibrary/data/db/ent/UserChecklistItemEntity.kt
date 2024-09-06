package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_checklist_items")
class UserChecklistItemEntity {

    @Id
    var id: String = ""
    @Column(name = "checklist_item_id")
    var checklistItemId: String = ""
    @Column(name = "user_id")
    var userId: String = ""
}