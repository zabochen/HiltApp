package ua.ck.taras.hiltapp.data.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ActivityContext
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ConnectionStateInterceptor @Inject constructor(
    @ActivityContext context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!applicationContext.isOnline()) {
            throw NoInternetConnectionException()
        }
        return chain.proceed(chain.request())
    }
}

/**
 * NoInternetConnection Exception
 */
class NoInternetConnectionException : IOException()

/**
 * isOnline extension
 */
fun Context.isOnline(): Boolean {

    val connectivityManager: ConnectivityManager =
        this.applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    } else {
        connectivityManager.activeNetworkInfo?.apply {
            return (isConnected && (type == ConnectivityManager.TYPE_WIFI
                    || type == ConnectivityManager.TYPE_MOBILE
                    || type == ConnectivityManager.TYPE_ETHERNET))
        }
    }
    return false
}
