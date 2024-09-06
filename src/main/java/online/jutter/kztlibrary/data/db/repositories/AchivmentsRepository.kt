package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.*
import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.AchivmentsEntity
import online.jutter.kztlibrary.data.db.ent.UserAchivmentEntity

object AchivmentsRepository : BaseRepository<AchivmentsEntity>() {

    fun setAll(achivments: List<AchivmentsEntity>) = executeTransaction {
        removeAllInstances(AchivmentsEntity::class.java)
        removeAllInstances(UserAchivmentEntity::class.java)

        for (item in achivments) {
            persist(item)
        }
    }

    fun getAllUserAchivments(userId: String) = executeTransaction {
        getQuery<UserAchivmentEntity>("user" eq userId)
    }

    fun giveAchivment(icon: String, userId: String) = executeTransaction {
        val achivment = getQuery<AchivmentsEntity>("icon" eq icon).first()
        val userAchivment = getQuery<UserAchivmentEntity>(("user" eq userId) and ("achivment" eq achivment.id)).firstOrNull()
        if (userAchivment == null) {
            persist(
                UserAchivmentEntity().apply {
                    this.id = getUUID()
                    this.user = userId
                    this.achivment = achivment.id
                }
            )
        }
    }
}