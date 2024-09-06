package online.jutter.kztlibrary.domain.uscaseses.news

import online.jutter.kztlibrary.data.db.repositories.UsersRepository
import online.jutter.kztlibrary.domain.models.Central
import online.jutter.kztlibrary.domain.uscaseses.news.GetAllNewsUseCase

class GetCentralUseCase {

    private val getAllNewsUseCase = GetAllNewsUseCase()

    operator fun invoke(id: String): Central {
        val user = UsersRepository.getById(id)!!
        val news = getAllNewsUseCase()
        return Central(
            coins = user.coins,
            listNews = news
        )
    }
}