package com.example.demoproject.ViewModels

import com.example.demoproject.Models.TodosItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoproject.Service.DatabaseService
import com.example.demoproject.Service.TodosService

class HomeFragmentViewModel: ViewModel() {
    private var _todosList = MutableLiveData<List<TodosItem>?>()
    val todosList: MutableLiveData<List<TodosItem>?> get() = _todosList

    fun fetchTodosData() {
        TodosService.fetchTodosFromApi("https://jsonplaceholder.typicode.com/todos/") { todosItems ->
            _todosList.postValue(todosItems)
            if (todosItems != null) {
                storeTodosInDatabase(todosItems)
            }
        }
    }

    fun storeTodosInDatabase(todolist: List<TodosItem>) {
        todolist.forEach { todoItem ->
            DatabaseService.saveItem(todoItem.completed, todoItem.id, todoItem.userId, todoItem.title)
        }
    }

    fun getData() {
        _todosList.postValue(DatabaseService.readData())
    }
}