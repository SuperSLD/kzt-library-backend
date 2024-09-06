package online.jutter.kztlibrary.domain.models.documents

import online.jutter.kztlibrary.data.db.ent.DocumentEntity
import online.jutter.kztlibrary.data.db.ent.RequestEntity

data class DocumentsRequestResponse(
    val documents: List<DocumentEntity>,
    val requests: List<RequestEntity>,
)