package br.com.joviniano.restwithspringboot.helpers

fun convertToDouble(numberStr: String?): Double {
    if (numberStr.isNullOrBlank()) return 0.0
    val number = numberStr.replace(',', '.')
    return if (isNumeric(number)) number.toDouble() else 0.0
}

fun isNumeric(numberStr: String?): Boolean {
    if (numberStr.isNullOrBlank()) return false
    val number = numberStr.replace(',', '.')
    return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
}
