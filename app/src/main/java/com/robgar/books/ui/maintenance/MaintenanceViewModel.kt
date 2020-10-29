package com.robgar.books.ui.maintenance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.robgar.books.data.repository.FirebaseMaintenanceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MaintenanceViewModel : ViewModel() {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    val maintenance = MutableLiveData<Int>()

    fun getMaintenance() {
        coroutineScope.launch {
            FirebaseMaintenanceService.getMaintenance()?.collect {
                maintenance.postValue(it)
            }
        }
    }
}