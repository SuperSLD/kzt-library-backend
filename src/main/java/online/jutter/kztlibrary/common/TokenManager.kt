package online.jutter.kztlibrary.common

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import online.jutter.kztlibrary.common.ext.digestSHA256
import online.jutter.kztlibrary.data.db.ent.UserEntity
import online.jutter.kztlibrary.data.db.repositories.UsersRepository
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object TokenManager {

    private const val TOKEN_LIFE = 360 * 20

    private const val jwtSecret = "05kndYRhgN9YcK2SCIS_8_LCs0jfptIyft3NKcKGY-gKwkSqTdE9f2azuFCLvBL_NHc83OrkvJYVz6kgRImuRA"

    private const val PREFIX = "Bearer "
    private const val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrtuvwxyz0123456789"
    private const val SECRET_PART_LEN = 200

    private fun generateSecretPart(): String {
        val builder = StringBuilder()
        var count = SECRET_PART_LEN
        while (count-- != 0) {
            val character = (Math.random() * ALPHA_NUMERIC_STRING.length).toInt()
            builder.append(ALPHA_NUMERIC_STRING[character])
        }
        return builder.toString()
    }

    /**
     * Получение токена для пользователя.
     * @param user пользователь.
     * @return строка токена.
     */
    fun getToken(user: UserEntity): String {
        val date = Date.from(LocalDate.now().plusDays(TOKEN_LIFE.toLong()).atStartOfDay(ZoneId.systemDefault()).toInstant())

        val tokenData = HashMap<String, Any>()

        tokenData["login"] = user.login
        tokenData["id"] = user.id
        tokenData["exp"] = date.time
        tokenData["digest"] = user.login.digestSHA256()

        return Jwts.builder()
                .setSubject(user.login)
                .setId(user.id)
                .setExpiration(date)
                .setClaims(tokenData)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    /**
     * Проверка токена на истинность.
     * @return true если токен правильный.
     */
    fun verifyToken(token: String): Boolean {
        return try {
            val clearedToken = token.replace(PREFIX.toRegex(), "")
            try {
                val user = UsersRepository.getByLogin(getLoginFromToken(clearedToken))
                user?.id == getIdFromToken(token)
            } catch (ex: Exception) {
                if (ex is IllegalArgumentException) throw ex
                false
            }
        } catch (ex: JwtException) {
            ex.printStackTrace()
            false
        } catch (ex: Exception) {
            if (ex is IllegalArgumentException) throw ex
            ex.printStackTrace()
            false
        }

    }

    /**
     * Получение логина из токена.
     * @return логин.
     */
    fun getLoginFromToken(token: String): String {
        val clearedToken = token.replace(PREFIX.toRegex(), "")
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(clearedToken).body
        return claims["login"] as String
    }

    /**
     * Получение id пользоваеля.
     * @return id.
     */
    fun getIdFromToken(token: String): String {
        val clearedToken = token.replace(PREFIX.toRegex(), "")
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(clearedToken).body
        return claims["id"] as String
    }

    /**
     * Получение digest пользоваеля.
     * @return digest.
     */
    fun getDigestFromToken(token: String): String {
        val clearedToken = token.replace(PREFIX.toRegex(), "")
        val claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(clearedToken).body
        return claims["digest"] as String
    }
}