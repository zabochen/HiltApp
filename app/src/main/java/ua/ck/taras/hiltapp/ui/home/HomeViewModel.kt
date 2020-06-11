package ua.ck.taras.hiltapp.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.ck.taras.hiltapp.data.DataService

class HomeViewModel @ViewModelInject constructor(
    private val dataService: DataService
) : ViewModel() {

    var stateSaveUi: String? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getDataList() = dataService.getDataList()
}