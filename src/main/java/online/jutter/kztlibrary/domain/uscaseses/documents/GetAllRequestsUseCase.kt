package online.jutter.kztlibrary.domain.uscaseses.documents

import online.jutter.kztlibrary.data.db.repositories.DocumentsRepository

class GetAllRequestsUseCase {

    operator fun invoke() = DocumentsRepository.getAll()
}