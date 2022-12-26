package com.example.movieappmvvm.di.commonModule

import android.content.Context
import coil.map.Mapper
import com.example.movieappmvvm.core.HandleApiRequest
import com.example.movieappmvvm.core.ProvideResources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CommonModule  {

    @Provides
    fun provideResources(@ApplicationContext context: Context): ProvideResources =
        ProvideResources.Base(context)

    @Provides
    fun provideDispatchers(): com.example.movieappmvvm.core.Dispatchers = com.example.movieappmvvm.core.Dispatchers.Base()

    @Provides
    fun provideBaseApiRequest(provideResources: ProvideResources): HandleApiRequest =
        HandleApiRequest.Base(provideResources)

//    @Provides
//    @Named("stringMapper")
//    fun provideDateFormat(): Mapper<String , String> = AbstractDateFormat.DateFormatter()

}