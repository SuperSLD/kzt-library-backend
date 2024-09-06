package online.jutter.kztlibrary.domain.uscaseses.documents

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.RequestEntity
import online.jutter.kztlibrary.data.db.ent.RequestPageEntity
import online.jutter.kztlibrary.data.db.ent.RequestPageLineEntity
import online.jutter.kztlibrary.data.db.repositories.DocumentsRepository
import online.jutter.kztlibrary.domain.models.documents.Request
import online.jutter.kztlibrary.domain.models.documents.RequestPage
import online.jutter.kztlibrary.domain.models.documents.RequestPageLine

class SetRequestsUseCase {

    operator fun invoke(requests: List<Request>) {
        DocumentsRepository.setAllRequests(requests.map { mapToEnt(it) })
    }

    private fun mapToEnt(request: Request) =
       RequestEntity().apply {
           id = getUUID()
           title = request.title
           icon = request.icon
           pages = request.pages.map { mapToEnt(it, id) }.toSet()
       }

    private fun mapToEnt(page: RequestPage, requestId: String) =
        RequestPageEntity().apply {
            id = getUUID()
            number = page.number
            this.requestId = requestId
            lines = page.lines.map { mapToEnt(it, id) }.toSet()
        }

    private fun mapToEnt(line: RequestPageLine, pageId: String) =
        RequestPageLineEntity().apply {
            id = getUUID()
            this.pageId = pageId
            type = line.type
            content = line.content
            required = line.required
            number = line.number
        }
}