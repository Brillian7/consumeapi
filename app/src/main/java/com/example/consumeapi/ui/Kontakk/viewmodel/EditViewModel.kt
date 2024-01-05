package com.example.consumeapi.ui.Kontakk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumeapi.repositori.KontakRepository
import com.example.consumeapi.ui.Kontakk.screen.EditDestination
import com.example.consumeapi.ui.Kontakk.viewmodel.InsertUiEvent
import com.example.consumeapi.ui.Kontakk.viewmodel.InsertUiState
import com.example.consumeapi.ui.Kontakk.viewmodel.toUiStateKontak
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
) : ViewModel(){

    var editKontakState by mutableStateOf(InsertUiState())
        private set
    val kontakId: Int = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            editKontakState = kontakRepository.getKontakById(kontakId).toUiStateKontak()
        }
    }

    fun updateInsertKontakState(insertUiEvent: InsertUiEvent){
        editKontakState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateKontak(){
        viewModelScope.launch {
            try {
                kontakRepository.updateKontak(kontakId,editKontakState.insertUiEvent.toKontak())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}