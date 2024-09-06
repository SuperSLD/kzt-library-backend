package online.jutter.kztlibrary.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "lessons")
class LessonEntity {

    @Id
    var id: String = ""
    var title: String = ""
    var description: String = ""

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "lesson_id")
    var pages: Set<LessonPageEntity> = setOf()

    @Column(name = "course_id")
    var courseId: String = ""

    var number: Int = 0

    fun getItemNumber() = number
}