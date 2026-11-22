package com.duoc.prerenderrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.prerenderrack.model.Rack
import com.duoc.prerenderrack.repository.RackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RackViewModel : ViewModel(){
    private val repository = RackRepository()

    private val _racks = MutableStateFlow<List<Rack>>(emptyList())
    val racks: StateFlow<List<Rack>> = _racks

    init {
        fetchRacks()
    }

    private fun fetchRacks() {
        viewModelScope.launch {
            _racks.value = repository.getRacks()
        }
    }

}