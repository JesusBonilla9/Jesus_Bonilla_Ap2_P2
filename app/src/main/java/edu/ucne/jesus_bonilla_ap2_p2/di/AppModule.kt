package edu.ucne.jesus_bonilla_ap2_p2.di

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.jesus_bonilla_ap2_p2.data.examen.remote.ExamenApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): ExamenApi{
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExamenApi::class.java)
    }
}