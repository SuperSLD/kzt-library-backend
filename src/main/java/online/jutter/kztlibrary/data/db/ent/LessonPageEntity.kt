package online.jutter.kztlibrary.data.db.ent

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.*

@Entity
@Table(name = "lesson_pages")
class LessonPageEntity {

    @Id
    var id: String = ""
    var description: String = ""
    var image: String = ""
    @Column(name = "is_answer")
    var isAnswer: Boolean = false
    @Column(name = "correct_answer")
    var correctAnswer: String = ""

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    @JoinColumn(name = "lesson_page_id")
    var answers: Set<LessonPageAnswerEntity> = setOf()

    @Column(name = "lesson_id")
    var lessonId: String = ""

    var number: Int = 0

    fun getItemNumber() = number

    fun getCorrectAnswerId() =
        answers.find { it.title == correctAnswer }?.id
}