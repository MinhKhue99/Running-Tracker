package com.minhkhue.runningtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemRowRecommendationBinding
import com.minhkhue.runningtracker.model.Recipes
import com.minhkhue.runningtracker.ui.fragment.FoodFragmentDirections

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(recipes: Recipes) {
            binding.tvMeal.text = recipes.strMeal
            binding.tvArea.text = recipes.strArea
            binding.tvCategory.text = recipes.strCategory
            Glide.with(itemView.context)
                .load(recipes.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMeal)

            itemView.setOnClickListener {
                val direction =
                    FoodFragmentDirections.actionFoodFragmentToDetailFoodFragment(recipes)
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
            ItemRowRecommendationBinding.inflate(
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