package online.jutter.kztlibrary.data.db.repositories

import online.jutter.kztlibrary.data.db.BaseRepository
import online.jutter.kztlibrary.data.db.ent.UserEntity
import online.jutter.kztlibrary.data.db.eq
import online.jutter.kztlibrary.data.db.getQuery

object UsersRepository : BaseRepository<UserEntity>() {

    fun getByLogin(login: String) = executeTransaction {
        getQuery<UserEntity>("login" eq login).firstOrNull()
    }
}