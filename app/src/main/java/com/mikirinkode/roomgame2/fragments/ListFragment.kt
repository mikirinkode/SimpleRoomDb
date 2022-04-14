package com.mikirinkode.roomgame2.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.roomgame2.GameViewModel
import com.mikirinkode.roomgame2.R
import com.mikirinkode.roomgame2.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        mViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        val listAdapter = ListAdapter()

        binding.rvGame.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        mViewModel.getAllData().observe(viewLifecycleOwner, Observer { game ->
            listAdapter.setData(game)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_menu){
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){ _, _, ->
                mViewModel.deleteAllData()
                Toast.makeText(requireContext(), "Successfully delete all data", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No"){ _, _ -> }
            builder.setTitle("Hapus semua data?")
            builder.setMessage("Are you sure want to delete all data? There is no undo button")
            builder.create().show()
        }

        return super.onOptionsItemSelected(item)
    }
}