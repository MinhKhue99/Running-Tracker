package com.minhkhue.runningtracker.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemRowRecipesBinding
import com.minhkhue.runningtracker.model.Recipes
import com.minhkhue.runningtracker.ui.fragment.FilterRecipesFragmentDirections

class FilteredRecipesAdapter : RecyclerView.Adapter<FilteredRecipesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(recipes: Recipes) {
            val colors = arrayOf(
                Color.parseColor("#FF9AA2"),
                Color.parseColor("#FFB7B2"),
                Color.parseColor("#FFDAC1"),
                Color.parseColor("#E2F0CB"),
                Color.parseColor("#B5EAD7"),
                Color.parseColor("#C7CEEA"),
                Color.parseColor("#E7E6CE")
            )
            val randomColor = colors.random()
            binding.cvRecipe.setCardBackgroundColor(randomColor)

            binding.tvRecipe.text = recipes.strMeal
            Glide.with(itemView.context)
                .load(recipes.strMealThumb)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.ivRecipe)

            itemView.setOnClickListener {
                val direction =
                    FilterRecipesFragmentDirections.actionFilterRecipesFragmentToDetailFoodFragment(recipes)
                it.findNavController().navigate(direction)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Recipes>() {
        override fun areItemsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Recipes, newItem: Recipes): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Recipes>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRowRecipesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}