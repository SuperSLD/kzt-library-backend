package online.jutter.kztlibrary.domain.uscaseses.notification

import online.jutter.kztlibrary.data.db.repositories.NotificationsRepository
import online.jutter.kztlibrary.domain.models.notification.Notification

class SendNotificationUseCase {

    operator fun invoke(
        userId: String,
        title: String,
        text: String,
    ) {
        NotificationsRepository.addNotification(
            Notification(
                title = title,
                text = text,
                user = userId,
                type = "default",
            )
        )
    }

    operator fun invoke(
        userId: String,
        title: String,
        text: String,
        eventId: String,
    ) {
        NotificationsRepository.addNotification(
            Notification(
                title = title,
                text = text,
                user = userId,
                type = "event",
                event = eventId,
            )
        )
    }
}