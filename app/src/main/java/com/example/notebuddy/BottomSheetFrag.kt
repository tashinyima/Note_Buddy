package com.example.notebuddy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notebuddy.databinding.FragmentBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BottomSheetFrag : BottomSheetDialogFragment() {
    private var _binding : FragmentBottomsheetBinding ?= null
    private val binding get() = _binding !!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentBottomsheetBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onDestroyView() {

        // required as per rule coz it oncreateview till on DestroyView
        super.onDestroyView()
        _binding =null
    }


}