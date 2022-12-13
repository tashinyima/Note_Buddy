package com.example.notebuddy.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebuddy.databinding.ItemRowLayoutBinding
import com.example.notebuddy.myData.MyNotes
import java.text.SimpleDateFormat


class NoteAdapter(private val notes: ArrayList<MyNotes>) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    fun updateNotes(newNotes: List<MyNotes>) {
        differ.submitList(newNotes)
    }

    inner class MyViewHolder(private val binding: ItemRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notes: MyNotes) {
            binding.notes = notes
            binding.executePendingBindings()
        }
        var date: TextView = binding.tvDate
        var created: TextView = binding.tvCreated

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBind =
            ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentNotes = differ.currentList.get(position)
        holder.bind(currentNotes)
        val timestamp = differ.currentList[position].timeStamp

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
       val date = dateFormatter.parse(timestamp)
        var  ddd = date.year
        holder.date.text = date.toString()
        Log.d("time ",date.toString())

        val isModified = differ.currentList[position].isModified
        if (isModified) {
            holder.created.text = "Modified at"
        } else {
            holder.created.text = "Created at"
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<MyNotes>() {
        override fun areItemsTheSame(oldItem: MyNotes, newItem: MyNotes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyNotes, newItem: MyNotes): Boolean {
            return oldItem.equals(newItem)
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}