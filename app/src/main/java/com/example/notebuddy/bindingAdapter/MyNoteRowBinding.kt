package com.example.notebuddy.bindingAdapter

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.example.notebuddy.myData.MyNotes
import com.example.notebuddy.view.fragments.HomeFragmentDirections

class MyNoteRowBinding {

    companion object {
        @BindingAdapter("onMyNoteClickListener")
        @JvmStatic
        fun onMyNoteClickListener(layout: ConstraintLayout, myNotes: MyNotes) {
            layout.setOnClickListener {
                Log.d("onClickListener","Called")

                try {
                    val action = HomeFragmentDirections.gotoaddEditFragment(myNotes)
                    layout.findNavController().navigate(action)


                } catch (e: Exception) {
                    Log.d("onClickListener", e.toString())
                }
            }

        }

    }

}