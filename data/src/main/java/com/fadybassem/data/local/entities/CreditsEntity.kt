package com.fadybassem.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credits")
data class CreditsEntity(
    @PrimaryKey val id: Int,
    val cast: String?, // Will be stored as JSON string using TypeConverter
    val crew: String?, // Will be stored as JSON string using TypeConverter
)
