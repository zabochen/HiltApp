package ua.ck.taras.hiltapp.ui.dashboard

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.ck.taras.hiltapp.data.network.NoInternetConnectionException
import ua.ck.taras.hiltapp.repository.DashboardRepository

class DashboardViewModel @ViewModelInject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchNetworkData() = with(viewModelScope) {
        launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = dashboardRepository.fetchNetworkData().body()
                    if (!response.isNullOrEmpty()) {
                        response.forEach {
                            Log.i("DashboardViewModel", "fetchNetworkData: ${it.id}: ${it.title}")
                        }
                    } else {
                        Log.i("DashboardViewModel", "fetchNetworkData: response = NULL")
                    }
                } catch (e: Exception) {
                    if (e is NoInternetConnectionException) {
                        Log.i("DashboardViewModel", "fetchNetworkData: NoInternetConnectionException")
                    } else {
                        Log.i("DashboardViewModel", "fetchNetworkData: Network exception")
                    }
                }
            }
        }
    }
}