package com.example.demoproject

import TodosItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentViewModel: ViewModel() {
    private var _todosList = MutableLiveData<List<TodosItem>>()
     val todosList: LiveData<List<TodosItem>> get() = _todosList

    init {
        fetchTodosFromApi()
    }
    fun fetchTodosFromApi() {
        viewModelScope.launch {
            val todosService = TodosService()
            _todosList.postValue(todosService.fetchTodosFromApi("https://jsonplaceholder.typicode.com/todos/"))
        }
    }
}