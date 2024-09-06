package online.jutter.kztlibrary.domain.models

import online.jutter.kztlibrary.data.db.ent.NewsEntity

data class Central(
    val coins: Int,
    val listNews: MutableList<NewsEntity>
)
