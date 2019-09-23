package edu.gatech.score.orphanconnect.database.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Orphan")
class Orphan(
        @PrimaryKey
        val id: String,
        val firstName: String,
        val lastName: String,
        val lattitude: Double,
        val longitude: Double,
        val age: Int,
        val description: String
)
