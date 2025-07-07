package dev.prateekthakur.mobileatlas.domain.model

data class CountryPopulation(
    val country: String,
    val code: String,
    val iso3: String,
    val populationCounts: List<YearlyPopulation>
)
