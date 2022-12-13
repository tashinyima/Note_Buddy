package com.example.notebuddy.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notebuddy.R
import com.example.notebuddy.databinding.FragmentAddEditBinding
import com.example.notebuddy.myData.MyNotes
import com.example.notebuddy.view.NotesViewModels
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddEditFragment : Fragment() {
    private lateinit var title :String
    private var body : String ? = null

    private val args: AddEditFragmentArgs by navArgs()

    private var isNewNote = true
    private val viewModels: NotesViewModels by viewModels()
    private lateinit var _binding: FragmentAddEditBinding
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            isNewNote = args.myNote.isNewNote
        }catch (e: Exception) {
            Log.d("TAG",e.toString())
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEditBinding.inflate(layoutInflater, container, false)

        isNetNote()
        initMenu()
        setOnClickListener()
        return binding.root
    }

    private fun initMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.findItem(R.id.sort_menu).isVisible =false
                menu.findItem(R.id.delete_all_menu).isVisible=true
                menuInflater.inflate(R.menu.savedelete_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
              return  when(menuItem.itemId){
                    R.id.save_menu -> {
                        addNotes()
                        findNavController().navigate(R.id.action_addEditFragment_to_homeFragment)
                        Toast.makeText(context,"Successfully saved",Toast.LENGTH_SHORT).show()
                        true
                    }
                  R.id.delete_menu -> {
                      Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show()
                      true
                  }
                  R.id.share -> {
                      shareIntent()
                  }
                  else -> false

                }
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)
    }

    private fun shareIntent(): Boolean {

        title = binding.etTitle.text.toString().trim()
        body = binding.etDescription.text.toString().trim()

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, title)
            putExtra(Intent.EXTRA_TEXT,body)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

        return true

    }

    private fun isNetNote() {

        if (!isNewNote) {
            try {
                binding.etTitle.setText(args.myNote.title)
                binding.etDescription.setText(args.myNote.description)
                binding.btnAdd.setText("Update Note")
            }catch (e: Exception) {
                Log.d("TAG",e.toString())
            }



        }
    }


    private fun addNotes() {

        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm");
        val date = Calendar.getInstance().time
        val timestamp = formatter.format(Timestamp(date.time))
        Log.d("time", timestamp.toString())
         title = binding.etTitle.text.toString()
         body = binding.etDescription.text.toString()

        if (!isNewNote) {
            val noteId = args.myNote.id.toString()
            val updateNote = MyNotes(title, body, timestamp, false, true, Integer.parseInt(noteId))
            viewModels.updateNote(updateNote)

        } else {

            if (!title.isNullOrEmpty() || !body.isNullOrEmpty()) {
                val newNote = MyNotes(title, body, timestamp, false, false)
                viewModels.addNote(newNote)
            } else {
                Toast.makeText(context, "Thank you", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setOnClickListener() {
        binding.btnAdd.setOnClickListener {
             addNotes()
            findNavController().navigate(R.id.action_addEditFragment_to_homeFragment)

        }
    }

}