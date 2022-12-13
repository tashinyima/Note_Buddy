package com.example.notebuddy.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.notebuddy.myData.MyNotes


class NoteDiffUtilCallBack (private val  oldNoteList: List<MyNotes>,
                            private val newNoteList: List<MyNotes>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldNoteList.size
    }

    override fun getNewListSize(): Int {
        return newNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
     return  oldNoteList[oldItemPosition]=== newNoteList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition]== newNoteList[newItemPosition]
    }
}