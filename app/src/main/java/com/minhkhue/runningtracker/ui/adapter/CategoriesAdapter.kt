package com.minhkhue.runningtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemGridCategoriesBinding
import com.minhkhue.runningtracker.model.Categories
import com.minhkhue.runningtracker.ui.fragment.FoodFragmentDirections

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemGridCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(categories: Categories) {
            binding.tvCategory.text = categories.strCategory
            Glide.with(itemView.context)
                .load(categories.strCategoryThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivCategory)

            itemView.setOnClickListener {
                val direction =
                    FoodFragmentDirections.actionFoodFragmentToFilterRecipesFragment(categories)
                it.findNavController().navigate(direction)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.strCategory == newItem.strCategory
        }

        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Categories>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGridCategoriesBinding.inflate(
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