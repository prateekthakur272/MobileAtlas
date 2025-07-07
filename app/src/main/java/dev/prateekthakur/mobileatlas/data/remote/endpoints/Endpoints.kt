package dev.prateekthakur.mobileatlas.data.remote.endpoints

object EndpointsBase{
    const val BASE_URL = "https://countriesnow.space"
}

object EndpointsPopulation {
    const val COUNTRIES = "${EndpointsBase.BASE_URL}/api/v0.1/countries/capital"
    const val COUNTRY_POPULATION = "${EndpointsBase.BASE_URL}/api/v0.1/countries/population"
    const val COUNTRY_POSITIONS = "${EndpointsBase.BASE_URL}/api/v0.1/countries/positions"
    const val COUNTRY_FLAG = "${EndpointsBase.BASE_URL}/api/v0.1/countries/flag/images"
    const val COUNTRY_CURRENCY = "${EndpointsBase.BASE_URL}/api/v0.1/countries/currency"
    const val COUNTRY_CITIES = "${EndpointsBase.BASE_URL}/api/v0.1/countries/cities"
    const val COUNTRY_DIAL_CODE = "${EndpointsBase.BASE_URL}/api/v0.1/countries/codes"
}