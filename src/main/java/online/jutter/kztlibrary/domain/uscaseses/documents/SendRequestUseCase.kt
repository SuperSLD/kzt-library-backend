package online.jutter.kztlibrary.domain.uscaseses.documents

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.DocumentEntity
import online.jutter.kztlibrary.data.db.repositories.DocumentsRepository
import online.jutter.kztlibrary.domain.models.documents.SendRequest

class SendRequestUseCase {

    operator fun invoke(sendRequest: SendRequest, userId: String) {
        DocumentsRepository.createDocument(
            DocumentEntity().apply {
                id = getUUID()
                user = userId
                title = "Документы по отправленной заявке \"${sendRequest.title}\""
                deadline = "30.04.2023"
            }
        )
    }
}