import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
//fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

fun <T> List<T>.rollingWindow(windowSize: Int): List<List<T>> {
    val list = mutableListOf<List<T>>()
    this.forEachIndexed { index, item ->
        if (this.size >= index + windowSize) {
            val window = mutableListOf<T>()
            window.add(item)
            for (n in 1 until windowSize) {
                window.add(this[index + n])
            }
            list.add(window)
        }
    }
    return list
}
