package dev.prateekthakur.mobileatlas.domain.model

import com.google.gson.annotations.SerializedName

data class State(val name: String, @SerializedName("state_code") val stateCode: String)