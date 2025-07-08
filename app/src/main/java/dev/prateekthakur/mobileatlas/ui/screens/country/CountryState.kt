package dev.prateekthakur.mobileatlas.ui.screens.country

import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition
import dev.prateekthakur.mobileatlas.domain.model.State

sealed class CountryState {
    data object Loading : CountryState()
    data class Error(val error: String) : CountryState()
    data class Success(
        val country: Country,
        val population: CountryPopulation,
        val position: CountryPosition,
        val flagUrl: String,
        val currency: String,
        val cities: List<String>,
        val dialCode: String,
        val states: List<State>
    ) : CountryState()
}