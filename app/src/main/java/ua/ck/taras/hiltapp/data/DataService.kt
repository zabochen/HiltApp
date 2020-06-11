package ua.ck.taras.hiltapp.data

import javax.inject.Inject

class DataService @Inject constructor() {

    fun getDataList() = MutableList(100) { index ->
        "Data_$index"
    }
}