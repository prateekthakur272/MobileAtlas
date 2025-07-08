package dev.prateekthakur.mobileatlas.ui.screens.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.mobileatlas.domain.repository.PopulationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: PopulationRepository
) : ViewModel() {

    private val mutableState = MutableStateFlow<CountryState>(CountryState.Loading)
    val state: StateFlow<CountryState> = mutableState.asStateFlow()

    fun getCountryData(iso2: String) {
        viewModelScope.launch {
            mutableState.update { CountryState.Loading }
            try {
                val country = repository.getCountryCapital(iso2).getOrNull()!!
                val population = repository.getCountryPopulation(country.iso3).getOrNull()
                val position = repository.getCountryPosition(iso2).getOrNull()
                val flag = repository.getCountryFlag(iso2).getOrNull()
                val currency = repository.getCountryCurrency(iso2).getOrNull()
                val cities = repository.getCountryCities(iso2).getOrNull()
                val dialCode = repository.getCountryDialCode(iso2).getOrNull()
                val states = repository.getCountryStates(iso2).getOrNull()

                mutableState.update {
                    CountryState.Success(
                        country = country,
                        population = population!!,
                        position = position!!,
                        flagUrl = flag!!,
                        currency = currency!!,
                        cities = cities!!,
                        dialCode = dialCode!!,
                        states = states!!
                    )
                }
            } catch (e: Exception) {
                mutableState.update {
                    CountryState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }
}