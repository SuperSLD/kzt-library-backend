package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.BaseRepository
import online.jutter.kztlibrary.data.db.ent.SectionEntity
import online.jutter.kztlibrary.data.db.removeAllInstances

object SectionsRepository : BaseRepository<SectionEntity>() {

    fun setAll(list: List<SectionEntity>) = executeTransaction {
        removeAllInstances(SectionEntity::class.java)
        for (item in list) {
            persist(item)
        }
    }
}