package dev.prateekthakur.mobileatlas.ui.screens.country

import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition
import dev.prateekthakur.mobileatlas.domain.model.State

data class CountryState(
    val iso3: String? = null,
    val iso2: String? = null,
    val population: CountryPopulation? = null,
    val position: CountryPosition? = null,
    val flagUrl: String? = null,
    val currency: String? = null,
    val cities: List<String>? = null,
    val dialCode: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val states: List<State>? = null
)