package online.jutter.kztlibrary.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "courses")
class CourseEntity {

    @Id
    var id: String = ""
    var title: String = ""
    @Column(name = "is_new")
    var isNew: Boolean = false
    @Column(name = "is_important")
    var isImportant: Boolean = false
    @Column(name = "is_checklist")
    var isChecklist: Boolean = false
    var description: String = ""
    var tag: String = ""

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    var lessons: Set<LessonEntity> = setOf()

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "course_id")
    @Column(name = "checklist_items")
    var checklistItems: Set<ChecklistItemEntity> = setOf()
}