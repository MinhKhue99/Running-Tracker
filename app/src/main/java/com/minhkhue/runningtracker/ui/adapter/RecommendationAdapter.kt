package com.minhkhue.runningtracker.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemRowRecommendationBinding
import com.minhkhue.runningtracker.model.remote.Meal

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowRecommendationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(meal: Meal) {
            binding.tvMeal.text = meal.strMeal
            binding.tvArea.text = meal.strArea
            binding.tvCategory.text = meal.strCategory
            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMeal)
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var response: List<Meal>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

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
        holder.bindData(response[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.let {
                if (it != null) {
                    it(response[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = response.size

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }
}