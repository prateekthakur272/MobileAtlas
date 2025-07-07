package dev.prateekthakur.mobileatlas.data.remote.api

import dev.prateekthakur.mobileatlas.data.remote.endpoints.EndpointsPopulation
import dev.prateekthakur.mobileatlas.data.request.CountryDataRequest
import dev.prateekthakur.mobileatlas.data.response.CountryDataResponse
import dev.prateekthakur.mobileatlas.domain.model.Country
import dev.prateekthakur.mobileatlas.domain.model.CountryPopulation
import dev.prateekthakur.mobileatlas.domain.model.CountryPosition
import dev.prateekthakur.mobileatlas.domain.model.CountryStates
import dev.prateekthakur.mobileatlas.domain.model.State
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CountriesDataApi {
    @GET(EndpointsPopulation.COUNTRIES)
    suspend fun getCountries() : CountryDataResponse<List<Country>>

    @POST(EndpointsPopulation.COUNTRIES)
    suspend fun getCountry(@Body data: CountryDataRequest) : CountryDataResponse<Country>

    @POST(EndpointsPopulation.COUNTRY_POPULATION)
    suspend fun getCountryPopulation(@Body data: CountryDataRequest) : CountryDataResponse<CountryPopulation>

    @POST(EndpointsPopulation.COUNTRY_POSITIONS)
    suspend fun getCountryPosition(@Body data: CountryDataRequest) : CountryDataResponse<CountryPosition>

    @POST(EndpointsPopulation.COUNTRY_FLAG)
    suspend fun getCountryFlag(@Body data: CountryDataRequest) : CountryDataResponse<Map<String, Any>>

    @POST(EndpointsPopulation.COUNTRY_CURRENCY)
    suspend fun getCountryCurrency(@Body data: CountryDataRequest) : CountryDataResponse<Map<String, Any>>

    @POST(EndpointsPopulation.COUNTRY_CITIES)
    suspend fun getCountryCities(@Body data: CountryDataRequest) : CountryDataResponse<List<String>>

    @POST(EndpointsPopulation.COUNTRY_DIAL_CODE)
    suspend fun getCountryDialCode(@Body data: CountryDataRequest) : CountryDataResponse<Map<String, Any>>

    @POST(EndpointsPopulation.COUNTRY_STATES)
    suspend fun getCountryStates(@Body data: CountryDataRequest) : CountryDataResponse<CountryStates>
}