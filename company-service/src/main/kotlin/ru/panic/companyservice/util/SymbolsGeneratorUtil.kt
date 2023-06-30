package ru.panic.companyservice.util

import java.security.SecureRandom


object SymbolsGeneratorUtil {
    private const val CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789%^&!@()-+=*#$"
    fun generateRandomSymbols(): String {
        val random = SecureRandom()
        val sb = StringBuilder()
        for (i in 0..5) {
            val randomIndex = random.nextInt(CHARACTERS.length)
            val randomChar = CHARACTERS[randomIndex]
            sb.append(randomChar)
        }
        return sb.toString()
    }
}
