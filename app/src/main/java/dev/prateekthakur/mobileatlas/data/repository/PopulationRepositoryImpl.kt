package dev.prateekthakur.mobileatlas.data.repository
import android.util.Log
import dev.prateekthakur.mobileatlas.data.remote.api.CountriesDataApi
import dev.prateekthakur.mobileatlas.data.request.CountryDataRequest
import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition
import dev.prateekthakur.mobileatlas.domain.model.State
import dev.prateekthakur.mobileatlas.domain.repository.PopulationRepository

class PopulationRepositoryImpl(private val api: CountriesDataApi) : PopulationRepository {

    override suspend fun getCountryCapital(): Result<List<Country>> {
        return withResult {
            val response = api.getCountries()
            response.data
        }
    }

    override suspend fun getCountryCapital(iso2: String): Result<Country> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountry(request)
            response.data
        }
    }

    override suspend fun getCountryPopulation(iso3: String): Result<CountryPopulation> {
        return withResult {
            val request = CountryDataRequest(iso3 = iso3)
            val response = api.getCountryPopulation(request)
            response.data
        }
    }

    override suspend fun getCountryPosition(iso2: String): Result<CountryPosition> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryPosition(request)
            response.data
        }
    }

    override suspend fun getCountryFlag(iso2: String): Result<String> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryFlag(request)
            response.data["flag"] as String
        }
    }

    override suspend fun getCountryCurrency(iso2: String): Result<String> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryCurrency(request)
            response.data["currency"] as String
        }
    }

    override suspend fun getCountryCities(iso2: String): Result<List<String>> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryCities(request)
            response.data
        }
    }

    override suspend fun getCountryDialCode(iso2: String): Result<String> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryDialCode(request)
            response.data["dial_code"] as String
        }
    }

    override suspend fun getCountryStates(iso2: String): Result<List<State>> {
        return withResult {
            val request = CountryDataRequest(iso2 = iso2)
            val response = api.getCountryStates(request)
            response.data.states
        }
    }
}

suspend fun <T> withResult(apiCall: suspend () -> T): Result<T> {
    return try {
        val result = apiCall()
        Result.success(result)
    } catch (e: Exception) {
        Log.d("PopulationRepositoryImpl", e.toString())
        Result.failure(e)
    }
}