package com.nestor.ocrinventarioa12

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryViewModel(application: Application) :
    AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).inventoryDao()

    val allItems: LiveData<List<InventoryEntity>> = dao.getAll()

    fun insert(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(InventoryEntity(text = text))
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAll()
        }
    }

    fun delete(item: InventoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(item)
        }
    }
}
