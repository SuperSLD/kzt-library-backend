package online.jutter.kztlibrary.domain.uscaseses.documents

import online.jutter.kztlibrary.data.db.repositories.DocumentsRepository

class GetUserDocumentsUseCase {

    operator fun invoke(userId: String) = DocumentsRepository.getUserDocuments(userId)
}