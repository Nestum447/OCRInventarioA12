package com.nestor.ocrinventarioa12

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InventoryDao {

    @Insert
    suspend fun insert(item: InventoryEntity)

    @Query("SELECT * FROM inventory ORDER BY id DESC")
    fun getAll(): LiveData<List<InventoryEntity>>

    @Query("DELETE FROM inventory")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(item: InventoryEntity)
}
