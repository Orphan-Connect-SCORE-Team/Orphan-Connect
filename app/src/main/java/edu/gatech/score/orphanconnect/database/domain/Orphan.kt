package edu.gatech.score.orphanconnect.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Orphan")
class Orphan(
        @PrimaryKey
        val id: String,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val sex: String,
        val description: String? = null,
        val photoUrl: String? = null,
        val refugeeCamp: String? = null,
        val village: String? = null,
        val LGA: String? = null,
        val county: String? = null,
        val motherName: String? = null,
        val fatherName: String? = null
)
