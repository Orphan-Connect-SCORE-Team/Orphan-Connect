package edu.gatech.score.orphanconnect.database.converters

import androidx.room.TypeConverter
import edu.gatech.score.orphanconnect.database.domain.Sex

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
            Sex.valueOf(type.toUpperCase())
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
