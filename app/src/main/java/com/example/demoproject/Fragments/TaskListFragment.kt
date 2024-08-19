package com.example.demoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.Adapters.TaskDetailsFragmentAdapter
import com.example.demoproject.Models.TodosItem
import com.example.demoproject.ViewModels.HomeFragmentViewModel
import com.example.demoproject.databinding.FragmentHomeBinding

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: TaskDetailsFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchTodosData()
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)

        val adapter = TaskDetailsFragmentAdapter(emptyList())
        binding.homeRecycleView.adapter = adapter

        viewModel.todosList.observe(viewLifecycleOwner, Observer { todosList ->
            if (todosList != null) {
                adapter.updateTodos(todosList)
            }
//            binding.homeRecycleView.adapter = todosList?.let { TaskDetailsFragmentAdapter(it) }
        })
        return binding.root
    }
}