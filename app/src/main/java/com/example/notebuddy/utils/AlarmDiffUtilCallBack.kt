package com.example.notebuddy.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.notebuddy.myData.Alarm

class AlarmDiffUtilCallBack(private val oldList: List<Alarm>, private val newList: List<Alarm>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition]===newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }
}