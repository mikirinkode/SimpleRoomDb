package com.mikirinkode.roomgame2.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mikirinkode.roomgame2.GameViewModel
import com.mikirinkode.roomgame2.R
import com.mikirinkode.roomgame2.databinding.FragmentUpdateBinding
import com.mikirinkode.roomgame2.model.GameEntity

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        mViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        binding.apply {
            args.currentItem.apply {
                etUpdateName.setText(name.toString())
                etUpdateGenre.setText(genre.toString())
                etUpdatePrice.setText(price.toString())
            }

            btnUpdate.setOnClickListener {
                updateData()
            }
        }
    }

    private fun updateData() {
        binding.apply {
            val name = etUpdateName.text
            val genre = etUpdateGenre.text
            val price = etUpdatePrice.text
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(genre) && price.isEmpty()){
                Toast.makeText(requireContext(), "Field is Empty", Toast.LENGTH_SHORT).show()
            } else {
                val game = GameEntity(args.currentItem.id, name.toString(), genre.toString(), Integer.parseInt(price.toString()))
                mViewModel.update(game)
                Toast.makeText(requireContext(), "Successfully update data", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_menu){
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){ _, _, ->
                mViewModel.delete(args.currentItem)
                Toast.makeText(requireContext(), "Successfully delete ${args.currentItem.name}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
            builder.setNegativeButton("No"){ _, _ -> }
            builder.setTitle("Delete ${args.currentItem.name}?")
            builder.setMessage("Are you sure want to delete ${args.currentItem.name}?")
            builder.create().show()
        }

        return super.onOptionsItemSelected(item)
    }
}