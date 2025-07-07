package dev.prateekthakur.mobileatlas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prateekthakur.mobileatlas.data.remote.api.CountriesDataApi
import dev.prateekthakur.mobileatlas.data.remote.endpoints.EndpointsBase
import dev.prateekthakur.mobileatlas.data.repository.PopulationRepositoryImpl
import dev.prateekthakur.mobileatlas.domain.repository.PopulationRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePopulationApi(): CountriesDataApi {
        return Retrofit.Builder()
            .baseUrl(EndpointsBase.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesDataApi::class.java)
    }

    @Provides
    @Singleton
    fun providePopulationRepository(api: CountriesDataApi): PopulationRepository {
        return PopulationRepositoryImpl(api)
    }
}