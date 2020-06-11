package ua.ck.taras.hiltapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class MainViewModel @ViewModelInject constructor() : ViewModel() {
    fun getViewModelFileName() = this.javaClass.name
}