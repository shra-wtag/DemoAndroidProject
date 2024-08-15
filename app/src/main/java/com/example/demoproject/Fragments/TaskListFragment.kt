package com.example.demoproject.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.Adapters.HomeFragmentAdapter
import com.example.demoproject.ViewModels.HomeFragmentViewModel
import com.example.demoproject.databinding.FragmentHomeBinding

class TaskListFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var adapter: HomeFragmentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        binding.homeRecycleView.layoutManager = LinearLayoutManager(context)

        viewModel.todosList.observe(viewLifecycleOwner, Observer { todosList ->
            binding.homeRecycleView.adapter = HomeFragmentAdapter(todosList)
        })
        return binding.root
    }
}