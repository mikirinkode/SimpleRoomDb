package com.mikirinkode.roomgame2.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mikirinkode.roomgame2.databinding.CustomRowBinding
import com.mikirinkode.roomgame2.model.GameEntity
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val gameList = ArrayList<GameEntity>()

    class ViewHolder(private val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(game: GameEntity){
            binding.apply {
                tvGameName.text = game.name
                tvGameGenre.text = game.genre
                tvGamePrice.text = game.price.toString()
                tvId.text = game.id.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val customRowBinding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(customRowBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]
        holder.bind(game)

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(game)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = gameList.size


    fun setData(newGame: List<GameEntity>){
        gameList.clear()
        gameList.addAll(newGame)
        notifyDataSetChanged()
    }


}