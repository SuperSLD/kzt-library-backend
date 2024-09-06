package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.documents.DocumentsRequestResponse
import online.jutter.kztlibrary.domain.models.documents.Request
import online.jutter.kztlibrary.domain.models.documents.SendRequest
import online.jutter.kztlibrary.domain.uscaseses.documents.GetAllRequestsUseCase
import online.jutter.kztlibrary.domain.uscaseses.documents.GetUserDocumentsUseCase
import online.jutter.kztlibrary.domain.uscaseses.documents.SendRequestUseCase
import online.jutter.kztlibrary.domain.uscaseses.documents.SetRequestsUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/documents"],
        produces = ["application/json; charset=utf-8"]
)
class DocumentsController {

    private val setRequestsUseCase = SetRequestsUseCase()
    private val getAllRequestsUseCase = GetAllRequestsUseCase()
    private val sendRequestsUseCase = SendRequestUseCase()
    private val getUserDocumentsController = GetUserDocumentsUseCase()

    @RequestMapping(
        value = ["getRequest"],
        method = [RequestMethod.GET]
    )
    fun get(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getAllRequestsUseCase()
    }

    @RequestMapping(
            value = ["setRequest"],
            method = [RequestMethod.POST]
    )
    fun setRequests(
        @RequestHeader("Authorization") token: String,
        @RequestBody requests: List<Request>,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        setRequestsUseCase(requests)
    }

    @RequestMapping(
        value = ["sendRequest"],
        method = [RequestMethod.POST]
    )
    fun sendRequests(
        @RequestHeader("Authorization") token: String,
        @RequestBody sendRequest: SendRequest,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        sendRequestsUseCase(sendRequest, TokenManager.getIdFromToken(token))
    }

    @RequestMapping(
        value = ["all"],
        method = [RequestMethod.GET]
    )
    fun all(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        DocumentsRequestResponse(
            getUserDocumentsController(TokenManager.getIdFromToken(token))!!,
            getAllRequestsUseCase()
        )
    }
}