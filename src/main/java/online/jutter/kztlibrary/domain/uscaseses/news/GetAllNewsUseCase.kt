package online.jutter.kztlibrary.domain.uscaseses.news



import online.jutter.kztlibrary.data.db.repositories.NewsRepository


class GetAllNewsUseCase {

    operator fun invoke() = NewsRepository.getAll()
}