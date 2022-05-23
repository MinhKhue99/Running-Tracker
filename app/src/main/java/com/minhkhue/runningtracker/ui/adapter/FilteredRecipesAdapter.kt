package com.minhkhue.runningtracker.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.ItemRowRecipesBinding
import com.minhkhue.runningtracker.model.remote.Meal

class FilteredRecipesAdapter : RecyclerView.Adapter<FilteredRecipesAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowRecipesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(meal: Meal) {
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

            binding.tvRecipe.text = meal.strMeal
            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.ivRecipe)
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
            ItemRowRecipesBinding.inflate(
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

    private var onItemClickListener: ((Meal) -> Unit)? = null

    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }
}