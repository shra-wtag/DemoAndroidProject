package com.example.demoproject.Adapters

import com.example.demoproject.Models.TodosItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.databinding.IncompleteItemListBinding
import com.example.demoproject.databinding.ItemListBinding
import io.realm.kotlin.query.RealmResults

class HomeFragmentAdapter(val todosList: RealmResults<TodosItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        if(todosList[position].completed) {
            return 0
        } else {
            return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val listItem = layoutInflater.inflate(R.layout.item_list, parent, false)

        if(viewType == 0) {
            val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CompletedTaskViewHolder(binding)
        } else {
            val binding = IncompleteItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return IncompletedTaskViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todosItem = todosList[position]

        if(getItemViewType(position) == 0) {
            (holder as CompletedTaskViewHolder).bindWithCompletedTasks(todosItem)
        } else {
            (holder as IncompletedTaskViewHolder).bindWithIncompletedTasks(todosItem)
        }
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    inner class CompletedTaskViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindWithCompletedTasks(todosItem: TodosItem) {
            binding.tvId.text = todosItem.id.toString()
            binding.tvTitle.text = todosItem.title
            binding.tvCompleted.text = todosItem.completed.toString()

            itemView.setOnClickListener {
                val bundle = bundleOf("task_details" to todosItem.title)
                it.findNavController().navigate(R.id.action_homeFragment_to_taskDetailsFragment,bundle)
            }
        }
    }

    inner class IncompletedTaskViewHolder(val binding: IncompleteItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindWithIncompletedTasks(todosItem: TodosItem) {
            binding.tvId.text = todosItem.id.toString()
            binding.tvTitle.text = todosItem.title
            binding.tvCompleted.text = todosItem.completed.toString()

            itemView.setOnClickListener {
                val bundle = bundleOf("task_details" to todosItem.title)
                it.findNavController().navigate(R.id.action_homeFragment_to_taskDetailsFragment,bundle)
            }
        }
    }
}