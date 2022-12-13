package com.example.notebuddy.view.ui.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.notebuddy.databinding.AlarmItemLayoutBinding
import com.example.notebuddy.myData.Alarm
import com.example.notebuddy.utils.AlarmDiffUtilCallBack

class AlarmAdapter( Context,private val alarmList: ArrayList<Alarm>): RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    private lateinit var diffUtilCallBack: AlarmDiffUtilCallBack




    fun setAlarm(oldList: List<Alarm>){
        diffUtilCallBack = AlarmDiffUtilCallBack(oldList,alarmList)


    }







    inner class ViewHolder(val binding: AlarmItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(alarm: Alarm) {
            binding.alarm =alarm
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = AlarmItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(alarmList.get(position))
    }

    override fun getItemCount(): Int {
       return alarmList.size
    }
}