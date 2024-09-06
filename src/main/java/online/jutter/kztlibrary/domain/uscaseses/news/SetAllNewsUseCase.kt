package online.jutter.kztlibrary.domain.uscaseses.news

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.NewsEntity
import online.jutter.kztlibrary.data.db.repositories.NewsRepository
import online.jutter.kztlibrary.domain.models.News

class SetAllNewsUseCase {

    operator fun invoke(news: List<News>) {
        NewsRepository.setAllNews(news.map { mapToEnt(it) })
    }

    fun mapToEnt(news: News) =
       NewsEntity().apply {
           this.id = getUUID()
           this.title = news.title
           this.description = news.description
           this.image = news.image
       }
}