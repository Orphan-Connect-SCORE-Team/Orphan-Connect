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
        val description: String?,
        val photoUrl: String?,
        val refugeeCamp: String?,
        val village: String?,
        val LGA: String?,
        val county: String?,
        val motherName: String?,
        val fatherName: String?
)
