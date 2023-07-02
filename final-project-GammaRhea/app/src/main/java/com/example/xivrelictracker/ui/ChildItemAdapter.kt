package com.example.xivrelictracker.ui

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xivrelictracker.R

class ChildItemAdapter : RecyclerView.Adapter<ChildItemAdapter.ViewHolder>() {
    var toDos = listOf<String>()

    override fun getItemCount() = toDos.size

    fun updateToDosList(newToDosList: List<String>?) {
        toDos = newToDosList ?: listOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_todo_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toDos[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val checkBox: CheckBox = itemView.findViewById(R.id.child_todo_checkbox)
        private val toDoTV: TextView = itemView.findViewById(R.id.tv_child_todo_text)

        init {

        }

        fun bind(toDo: String) {
            toDoTV.text = toDo
        }
    }
}