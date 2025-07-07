package dev.prateekthakur.mobileatlas.ui.screens.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition
import dev.prateekthakur.mobileatlas.domain.repository.PopulationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: PopulationRepository
) : ViewModel() {

    private val iso3 = "NGA"
    private val iso2 = "NG"

    private val _populationState = MutableStateFlow<CountryPopulation?>(null)
    private val _positionState = MutableStateFlow<CountryPosition?>(null)
    private val _flagState = MutableStateFlow<String?>(null)
    private val _currencyState = MutableStateFlow<String?>(null)
    private val _citiesState = MutableStateFlow<List<String>?>(null)
    private val _dialCodeState = MutableStateFlow<String?>(null)

    val populationState = _populationState.asStateFlow()
    val positionState = _positionState.asStateFlow()
    val flagState = _flagState.asStateFlow()
    val currencyState = _currencyState.asStateFlow()
    val citiesState = _citiesState.asStateFlow()
    val dialCodeState = _dialCodeState.asStateFlow()

    private fun getCountryPopulation() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryPopulation(iso3)
                _populationState.value = data.getOrNull()
            } catch (e: Exception) {
                _populationState.value = null
            }
        }
    }

    private fun getCountryPosition() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryPosition(iso2)
                _positionState.value = data.getOrNull()
            } catch (e: Exception) {
                _populationState.value = null
            }
        }
    }

    private fun getCountryFlag() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryFlag(iso2)
                _flagState.value = data.getOrNull()
            } catch (e: Exception) {
                _flagState.value = null
            }
        }
    }

    private fun getCountryCurrency() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryCurrency(iso2)
                _currencyState.value = data.getOrNull()
            } catch (e: Exception) {
                _currencyState.value = null
            }
        }
    }

    private fun getCountryCities() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryCities(iso2)
                _citiesState.value = data.getOrNull()
            } catch (e: Exception) {
                _citiesState.value = null
            }
        }
    }

    private fun getCountryDialCode() {
        viewModelScope.launch {
            try {
                val data = countryRepository.getCountryDialCode(iso2)
                _dialCodeState.value = data.getOrNull()
            } catch (e: Exception) {
                _dialCodeState.value = null
            }
        }
    }

    fun getCountryData() {
        getCountryPopulation()
        getCountryPosition()
        getCountryFlag()
        getCountryCurrency()
        getCountryCities()
        getCountryDialCode()
    }
}