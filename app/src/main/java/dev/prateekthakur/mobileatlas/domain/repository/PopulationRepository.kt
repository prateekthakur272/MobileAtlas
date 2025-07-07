package dev.prateekthakur.mobileatlas.domain.repository

import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition

interface PopulationRepository {
    suspend fun getCountryCapital(): Result<List<Country>>
    suspend fun getCountryPopulation(iso3: String): Result<CountryPopulation>
    suspend fun getCountryPosition(iso2: String): Result<CountryPosition>
    suspend fun getCountryFlag(iso2: String): Result<String>
    suspend fun getCountryCurrency(iso2: String): Result<String>
    suspend fun getCountryCities(iso2: String): Result<List<String>>
    suspend fun getCountryDialCode(iso2: String): Result<String>
}