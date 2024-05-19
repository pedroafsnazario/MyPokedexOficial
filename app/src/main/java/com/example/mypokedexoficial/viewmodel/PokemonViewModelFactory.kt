package com.example.mypokedexoficial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class PokemonViewModelFactory : ViewModelProvider.Factory {
     fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel() as T
    }
}