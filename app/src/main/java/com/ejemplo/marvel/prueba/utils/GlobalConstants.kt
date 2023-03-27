package com.ejemplo.marvel.prueba.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class GlobalConstants {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "9aa2d5c19958ee1e378d637a891ac07f"
        private const val PRIVATE_KEY = "0e5a42c64f37a7912f782f3c0e19981298894552"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

        const val HULK_ID = "1009351"
        const val THOR_ID = "1009664"
        const val IRON_MAN_ID = "1009368"
        const val CAPTAIN_AMERICA_ID = "1009220"

        const val PUNTO = "."
    }
}
