package com.example.guilhermecardoso.magicroutines

inline fun String?.whenNonEmpty(block: String.() -> Unit) {
    if (this?.isNotEmpty() ?: false) {
        this?.block()
    }
}