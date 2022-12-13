package com.example.notebuddy.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notebuddy.R
import com.example.notebuddy.adapter.NoteAdapter
import com.example.notebuddy.databinding.FragmentHomeBinding
import com.example.notebuddy.utils.SwipeToDeleteCallback
import com.example.notebuddy.view.NotesViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesViewModels by viewModels()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteAdapter = NoteAdapter(ArrayList())


    }

    private fun initMenu() {
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //menu.clear()
                menu.findItem(R.id.delete_all_menu).isVisible = false
                menuInflater.inflate(R.menu.home_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
              return  when (menuItem.itemId) {
                    R.id.profile -> {
                        Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show()
                        true
                    }
                  R.id.sort_menu -> {
                      sortItems()

                  }

                  else -> {false}
              }
            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

    }

    private fun sortItems(): Boolean {

        findNavController().navigate(R.id.gotobottomSheetFrag)
        return true

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        initMenu()
        setupRV()
        btnOnClickListener()
        fetchNotes()
        observeLiveData()
        return binding.root
    }


    private fun observeLiveData() {
        viewModel.getnotesLiveData.observe(viewLifecycleOwner, {
            noteAdapter.differ.submitList(it)
        })
    }

    private fun fetchNotes() {
        viewModel.loadNotes()
    }

    private fun btnOnClickListener() {
        binding.floatingBtn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.gotoaddEditFragment)
        }
    }

    private fun setupRV() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter;
            val swipeDelete = object : SwipeToDeleteCallback(context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    val position = viewHolder.bindingAdapterPosition
                    val note = noteAdapter.differ.currentList[position]
                    viewModel.deleteNote(note)
                }
            }

            val touchHelper = ItemTouchHelper(swipeDelete)
            touchHelper.attachToRecyclerView(this)

        }
    }

}