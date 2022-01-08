import dev.abhishekbansal.audiobook.network.ApiServiceProvider
import dev.abhishekbansal.audiobook.network.HttpClientProvider
import dev.abhishekbansal.audiobook.network.LoggingInterceptorProvider
import dev.abhishekbansal.audiobook.utils.MoshiProvider
import org.koin.dsl.module

val appModule = module {
    // Network
    single { LoggingInterceptorProvider.provide() }
    single { HttpClientProvider.provide(loggingInterceptor = get(), BuildConfig.DEBUG) }
    single { MoshiProvider.provide()}
    single { ApiServiceProvider.provide(baseUrl = "https://run.mocky.io", moshi = get(), httpClient = get()) }
}