package com.uwe.poundforpound.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val weight: Float? = null,
    val height: Float? = null,
    val gender: String? = null,
    val age: Int? = null
)