package com.example.notebuddy.myData

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "NotesTable")
data class MyNotes(
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "timestamp")
    val timeStamp: String?,
    @ColumnInfo(name = "isNewNote")
    val isNewNote : Boolean,
    @ColumnInfo(name = "isModified")
    var isModified : Boolean,
    @PrimaryKey(autoGenerate = true)
    var id: Int =0
):Parcelable
{

}
