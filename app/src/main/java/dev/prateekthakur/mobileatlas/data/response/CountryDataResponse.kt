package dev.prateekthakur.mobileatlas.data.response

data class CountryDataResponse<T>(
    val error: Boolean,
    val msg: String,
    val data:T
)