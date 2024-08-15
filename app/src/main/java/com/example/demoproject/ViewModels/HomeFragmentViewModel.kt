package com.example.demoproject.ViewModels

import com.example.demoproject.Models.TodosItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoproject.Service.DatabaseService
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private var _todosList = MutableLiveData<RealmResults<TodosItem>>()
    val todosList: LiveData<RealmResults<TodosItem>> get() = _todosList

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            val databaseService = DatabaseService()
            _todosList.postValue(databaseService.readData())
        }
    }
}