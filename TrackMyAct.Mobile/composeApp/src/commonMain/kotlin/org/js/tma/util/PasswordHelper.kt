package org.js.tma.util

enum class StrengthLevel {
    LOW,
    MEDIUM,
    HIGH
}

fun String.calculatePasswordStrength(): StrengthLevel {
    if (this.length < 8) return StrengthLevel.LOW

    var criteriaMet = 0

    if (this.any { it.isLowerCase() }) criteriaMet++
    if (this.any { it.isUpperCase() }) criteriaMet++
    if (this.any { it.isDigit() }) criteriaMet++
    if (this.any { !it.isLetterOrDigit() }) criteriaMet++

    return when {
        criteriaMet >= 4 -> StrengthLevel.HIGH
        criteriaMet >= 2 -> StrengthLevel.MEDIUM
        else -> StrengthLevel.LOW
    }
}