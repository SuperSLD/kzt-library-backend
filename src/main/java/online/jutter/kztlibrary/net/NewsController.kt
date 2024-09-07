package online.jutter.kztlibrary.net

import online.jutter.kztlibrary.common.TokenManager
import online.jutter.kztlibrary.common.ext.createEmptyWrapperResponse
import online.jutter.kztlibrary.common.ext.createWrapperResponse
import online.jutter.kztlibrary.domain.models.News
import online.jutter.kztlibrary.domain.uscaseses.news.GetAllNewsUseCase
import online.jutter.kztlibrary.domain.uscaseses.news.SetAllNewsUseCase
import online.jutter.kztlibrary.domain.uscaseses.notification.SendNotificationUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        value = ["api/news"],
        produces = ["application/json; charset=utf-8"]
)
class NewsController {

    private val setAllNewsUseCase = SetAllNewsUseCase()
    private val getAllNewsUseCase = GetAllNewsUseCase()
    private val sendNotificationUseCase = SendNotificationUseCase()

    @RequestMapping(
        value = ["getNews"],
        method = [RequestMethod.GET]
    )
    fun get(
        @RequestHeader("Authorization") token: String,
    ) = createWrapperResponse {
        TokenManager.verifyToken(token)
        getAllNewsUseCase()
    }

    @RequestMapping(
            value = ["setNews"],
            method = [RequestMethod.POST]
    )
    fun set(
        @RequestHeader("Authorization") token: String,
        @RequestBody news: List<News>,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        setAllNewsUseCase(news)
    }

    @RequestMapping(
        value = ["joinEvent/{id}"],
        method = [RequestMethod.GET]
    )
    fun joinEvent(
        @RequestHeader("Authorization") token: String,
        @PathVariable id: String,
    ) = createEmptyWrapperResponse {
        TokenManager.verifyToken(token)
        sendNotificationUseCase(
            TokenManager.getIdFromToken(token),
            "Запись на мероприятие",
            "Вы успешно записались на мероприятие! Ближе к началу организаторы с вами свяжутся.",
            id,
        )
    }
}