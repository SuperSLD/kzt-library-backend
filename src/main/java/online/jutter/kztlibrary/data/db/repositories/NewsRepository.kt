package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.BaseRepository
import online.jutter.kztlibrary.data.db.removeAllInstances
import online.jutter.kztlibrary.data.db.ent.NewsEntity


object NewsRepository : BaseRepository<NewsEntity>() {

    fun setAllNews(news: List<NewsEntity>) = executeTransaction {
        removeAllInstances(NewsEntity::class.java)

        for (item in news) {
            persist(item)
        }
    }

}