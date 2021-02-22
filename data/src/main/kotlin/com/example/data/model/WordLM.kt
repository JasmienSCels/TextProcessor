package com.example.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class WordLM(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val title: String,
    val freq: Int,
    val isPrime: Boolean,
    val createdAt: Long = System.currentTimeMillis()

)