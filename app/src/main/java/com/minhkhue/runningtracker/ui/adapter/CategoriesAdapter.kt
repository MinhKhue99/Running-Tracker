package com.minhkhue.runningtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemGridCategoriesBinding
import com.minhkhue.runningtracker.model.remote.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemGridCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(categories: Category) {
            binding.tvCategory.text = categories.strCategory
            Glide.with(itemView.context)
                .load(categories.strCategoryThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivCategory)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.strCategory == newItem.strCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var response: List<Category>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

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
        holder.bindData(response[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(response[position])
            }
        }
    }

    override fun getItemCount(): Int = response.size

    private var onItemClickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }
}