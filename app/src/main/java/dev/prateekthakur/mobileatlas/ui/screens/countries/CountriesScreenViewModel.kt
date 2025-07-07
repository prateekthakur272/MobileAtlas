package dev.prateekthakur.mobileatlas.ui.screens.countries

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.domain.repository.PopulationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountriesScreenViewModel @Inject constructor(
    private val populationRepository: PopulationRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<List<Country>>(listOf())
    val state = _state.asStateFlow()

    init {
        Log.d("CountryScreenViewModel", "CountryScreenViewModel initialized")
        getCountryPopulation()
    }

    private fun getCountryPopulation() {
        viewModelScope.launch {
            val data = populationRepository.getCountryCapital()
            _state.value = data.getOrNull()?: listOf()
        }
    }

}