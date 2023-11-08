package com.example.taskproject.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
 fun formatDate(createdAt: String?): String {
    if (createdAt.isNullOrBlank()) {
        return "Unknown"
    }
    val dateFormatter = DateTimeFormatter.ISO_DATE_TIME
    val createdDate = OffsetDateTime.parse(createdAt, dateFormatter)
    val now = OffsetDateTime.now()
    val difference = Duration.between(createdDate, now)

    return when {
        difference.toDays() >= 180 -> {
            return createdDate.format(DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy", Locale.getDefault()))
        }
        difference.toDays() >= 120 -> "4 months ago"
        else -> {
            val months = difference.toDays() / 30
            val years = months / 12
            return "$months months ago, $years years ago"
        }
    }

}