package com.example.xivrelictracker.ui

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.xivrelictracker.data.JobObject
import com.example.xivrelictracker.R
import java.io.InputStream

class JobAdapter(private val onClick: (JobObject) -> Unit) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    var jobList: List<JobObject> = listOf()

    fun updateJobList(newJobList: List<JobObject>) {
        jobList = newJobList ?: listOf()
        notifyDataSetChanged()
    }

    override fun getItemCount() = jobList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.job_selection_item, parent, false)
        return ViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jobList[position])
    }

    class ViewHolder(itemView: View, val onClick: (JobObject) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val iconIV: ImageView = itemView.findViewById(R.id.iv_job_icon)
        private val nameTV: TextView = itemView.findViewById(R.id.tv_job_name)
        private var currentJobObject: JobObject? = null

        init {
            itemView.setOnClickListener {
                currentJobObject?.let(onClick)
            }
        }

        val ctx = itemView.context

        fun bind(jobObject: JobObject) {
            currentJobObject = jobObject
            nameTV.text = currentJobObject!!.name
            //iconIV.setImageResource(R.drawable.pld_icon)
            //Wow this actually worked
            iconIV.setImageResource(ctx.resources.getIdentifier(currentJobObject!!.jobID, "drawable", ctx.packageName))
        }
    }
}