package com.example.consumeapi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.consumeapi.KontakAplikation
import com.example.consumeapi.ui.Kontakk.viewmodel.DetailsViewModel
import com.example.consumeapi.ui.Kontakk.viewmodel.EditViewModel
import com.example.consumeapi.ui.home.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiKontak().container.kontakRepository)
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                kontakRepository =  aplikasiKontak().container.kontakRepository
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiKontak().container.kontakRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): KontakAplikation =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakAplikation)