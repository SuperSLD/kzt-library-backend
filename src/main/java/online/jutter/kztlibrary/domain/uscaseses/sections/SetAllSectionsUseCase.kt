package online.jutter.kztlibrary.domain.uscaseses.sections

import online.jutter.kztlibrary.common.ext.getUUID
import online.jutter.kztlibrary.data.db.ent.SectionEntity
import online.jutter.kztlibrary.data.db.repositories.SectionsRepository
import online.jutter.kztlibrary.domain.models.section.CreateSection

class SetAllSectionsUseCase {

    operator fun invoke(list: List<CreateSection>) {
        SectionsRepository.setAll(
            list.map { section ->
                SectionEntity().apply {
                    id = getUUID()
                    title = section.title
                    description = section.description
                    schedule = section.schedule
                    cover = section.cover
                    author = section.author
                    subtitle = section.subtitle
                    url = section.link
                }
            }
        )
    }
}