package com.example.demoproject

import TodosItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.demoproject.databinding.FragmentHomeBinding

class HomeFragmentAdapter(val todosList: List<TodosItem>): RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.item_list, parent, false)
        return HomeFragmentViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        holder.tvComplete.text = todosList[position].completed.toString()
        holder.tvTitle.text = todosList[position].title
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    class HomeFragmentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvComplete = itemView.findViewById<TextView>(R.id.tvCompleted)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    }
}

