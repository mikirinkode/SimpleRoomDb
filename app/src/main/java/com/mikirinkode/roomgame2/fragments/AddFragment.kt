package com.mikirinkode.roomgame2.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mikirinkode.roomgame2.GameViewModel
import com.mikirinkode.roomgame2.R
import com.mikirinkode.roomgame2.databinding.FragmentAddBinding
import com.mikirinkode.roomgame2.model.GameEntity


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        binding.apply {
            btnAddGame.setOnClickListener {
                insertGame()
            }
        }
    }

    private fun insertGame() {
        binding.apply {
            val name = etGameName.text
            val genre = etGameGenre.text
            val price = etGamePrice.text
            if(TextUtils.isEmpty(name) && TextUtils.isEmpty(genre) && price.isEmpty()){
                Toast.makeText(requireContext(), "Field is Empty", Toast.LENGTH_SHORT).show()
            } else {
                val game = GameEntity(0, name.toString(), genre.toString(), Integer.parseInt(price.toString()))
                mViewModel.insert(game)
                Toast.makeText(requireContext(), "successfully insert data", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }
    }
}