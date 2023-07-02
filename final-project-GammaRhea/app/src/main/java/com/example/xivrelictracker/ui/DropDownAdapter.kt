package com.example.xivrelictracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xivrelictracker.R
import com.example.xivrelictracker.data.QuestObject

class DropDownAdapter(private val onClick: (QuestObject) -> Unit) : RecyclerView.Adapter<DropDownAdapter.ViewHolder>() {
    var quests: MutableList<QuestObject> = mutableListOf()

    fun updateQuestList(newQuestList: MutableList<QuestObject>) {
        quests = newQuestList ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun addQuestToList(quest: QuestObject, position: Int = 0) {
        this.quests.add(quest)
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.quests.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropDownAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drop_down_item, parent, false)

        val adapter = ChildItemAdapter()

        return ViewHolder(view, onClick, adapter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.quests[position])
    }

    class ViewHolder(itemView: View, val onClick: (QuestObject) -> Unit, adapter: ChildItemAdapter) : RecyclerView.ViewHolder(itemView) {
        private val questNameTV: TextView = itemView.findViewById(R.id.tv_quest_name)
        private val childRV: RecyclerView = (itemView.findViewById(R.id.rv_child_items))
        private val childAdapter = adapter

        val ctx = itemView.context

        private var currentQuest: QuestObject? = null

        init {
            itemView.setOnClickListener {
                currentQuest?.let(onClick)
            }

            childRV.layoutManager = LinearLayoutManager(ctx)
            childRV.setHasFixedSize(true)
            childRV.adapter = childAdapter
        }

        fun bind(questObject: QuestObject) {
            questNameTV.text = questObject.name
            childAdapter.updateToDosList(questObject.toDos)
        }
    }
}