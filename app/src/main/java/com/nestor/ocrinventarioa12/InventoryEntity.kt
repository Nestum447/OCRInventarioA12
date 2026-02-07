package com.nestor.ocrinventarioa12

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)
