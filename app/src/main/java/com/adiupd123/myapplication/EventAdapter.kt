package com.adiupd123.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.adiupd123.myapplication.databinding.ActivityMainBinding
import com.adiupd123.myapplication.databinding.EventItemBinding

class EventAdapter: RecyclerView.Adapter<EventAdapter.EventViewHolder>(){
    inner class EventViewHolder(val binding: EventItemBinding):
            RecyclerView.ViewHolder(binding.root)

    var events: List<Event> =  emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            EventItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = events.size
}