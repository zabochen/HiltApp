package ua.ck.taras.hiltapp.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.taras.hiltapp.repository.DashboardRepository

class DashboardViewModel @ViewModelInject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchNetworkData() = dashboardRepository.fetchNetworkData()
}