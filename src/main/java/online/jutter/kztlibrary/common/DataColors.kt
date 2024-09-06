package online.jutter.kztlibrary.common

object DataColors {
    private val COLORS = mutableListOf(
        "#5F9FFF", "#F2FF5F", "#5FFFE2", "#925FFF", "#FF855F",
        "#62FF5F", "#E95FFF", "#FF5F5F", "#FF5FAC", "#5F82FF"
    )

    fun getByHash(string: String) = getByHash(string.hashCode())

    fun getByHash(hash: Int) = COLORS[kotlin.math.abs(hash % COLORS.size)]
}