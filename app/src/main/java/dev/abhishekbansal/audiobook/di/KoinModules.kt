import dev.abhishekbansal.audiobook.BuildConfig
import dev.abhishekbansal.audiobook.list.AudioBookViewModel
import dev.abhishekbansal.audiobook.list.repository.AudioBookRepository
import dev.abhishekbansal.audiobook.list.repository.LocalDataSource
import dev.abhishekbansal.audiobook.list.repository.RemoteDataSource
import dev.abhishekbansal.audiobook.network.ApiServiceProvider
import dev.abhishekbansal.audiobook.network.HttpClientProvider
import dev.abhishekbansal.audiobook.network.LoggingInterceptorProvider
import dev.abhishekbansal.audiobook.utils.MoshiProvider
import dev.abhishekbansal.audiobook.utils.photoloader.GlideLoader
import dev.abhishekbansal.audiobook.utils.photoloader.PhotoLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Network
    single { LoggingInterceptorProvider.provide() }
    single { HttpClientProvider.provide(loggingInterceptor = get(), BuildConfig.DEBUG) }
    single { MoshiProvider.provide()}
    single { ApiServiceProvider.provide(baseUrl = "https://run.mocky.io", moshi = get(), httpClient = get()) }
    single<PhotoLoader> { GlideLoader() }

    // audio book module
    factory { RemoteDataSource(apiService = get()) }
    factory { LocalDataSource() }
    factory { AudioBookRepository(localDataSource = get(), remoteDataSource = get()) }
    viewModel { AudioBookViewModel(repository = get()) }
}