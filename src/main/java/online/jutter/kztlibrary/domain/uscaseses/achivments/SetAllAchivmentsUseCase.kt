package online.jutter.kztlibrary.domain.uscaseses.achivments

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.AchivmentsEntity
import online.jutter.kztlibrary.data.db.repositories.AchivmentsRepository
import online.jutter.kztlibrary.domain.models.Achivment

class SetAllAchivmentsUseCase {

    operator fun invoke(achivments: List<Achivment>) {
        AchivmentsRepository.setAll(achivments.map { mapToEnt(it) })
    }

    fun mapToEnt(achivment: Achivment) =
       AchivmentsEntity().apply {
           this.id = getUUID()
           this.title = achivment.title
           this.description = achivment.description
           this.icon = achivment.icon
       }
}