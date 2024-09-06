package online.jutter.kztlibrary.data.db.ent

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_finished_lessons")
class UserFinishedLessonEntity {

    @Id
    var id: String = ""
    @Column(name = "lesson_id")
    var lessonId: String = ""
    @Column(name = "user_id")
    var userId: String = ""
}