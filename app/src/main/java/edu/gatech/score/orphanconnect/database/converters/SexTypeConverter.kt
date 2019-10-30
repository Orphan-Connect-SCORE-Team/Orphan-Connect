package edu.gatech.score.orphanconnect.database.converters

import androidx.room.TypeConverter
import edu.gatech.score.orphanconnect.database.domain.Sex
import java.util.*

class SexTypeConverter {
    @TypeConverter
    fun sexToInt(type: Sex?): String? {
        return type?.name
    }

    @TypeConverter
    fun stringToSex(type: String?): Sex? {
        if (type.isNullOrBlank()) {
            return null
        }
        return try {
            Sex.valueOf(type.toUpperCase(Locale.ROOT))
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
