package com.minhkhue.runningtracker.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentDetailFoodBinding
import com.minhkhue.runningtracker.model.remote.Meal
import com.minhkhue.runningtracker.utils.Status
import com.minhkhue.runningtracker.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFoodFragment : Fragment() {

    private var _binding: FragmentDetailFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentMeal: Meal
    private var currentMealId = 0
    private lateinit var youtubeLink: String
    private val recipeViewModel: RecipeViewModel by viewModels()
    private val args: DetailFoodFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentMeal = args.meal
        currentMealId = currentMeal.idMeal!!.toInt()
        recipeViewModel.getRecipeDetails(currentMealId)
        Timber.d("CurrentMeal:  ${currentMeal.strMeal} CurrentMealId: $currentMealId")
        initView()
        initViewModel()
        initListener()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initView() {
        //handle color toolbar when scroll up
        binding.collapseToolbar.setExpandedTitleColor(Color.argb(0, 0, 0, 0))
        binding.collapseToolbar.setContentScrimColor(resources.getColor(R.color.white))
        //added icon to navigation
        binding.toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)

        binding.tvMeal.text = currentMeal.strMeal

        Glide.with(this)
            .load(currentMeal.strMealThumb)
            .placeholder(R.drawable.placeholder)
            .into(binding.ivBgMeal)

        Glide.with(this)
            .load(currentMeal.strMealThumb)
            .circleCrop()
            .placeholder(R.drawable.placeholder)
            .into(binding.ivMeal)

    }

    @SuppressLint("SetTextI18n")
    private fun initViewModel() {
        recipeViewModel.recipe.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoading(true)
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    Timber.d("Response: ${response.data}")
                    response.data?.let {
                        renderUI(it.meals[0])
                        val strIngredient = it.meals[0].getIngredients()
                        val strMeasure = it.meals[0].getMeasure()
                        binding.tvIngredients.text = strIngredient
                        binding.tvMeasures.text = strMeasure
                    }
                }
                Status.ERROR -> {
                    showLoading(false)
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun renderUI(meal: Meal) {
        binding.tvSubMeal.text =
            "${meal.strArea} | ${meal.strCategory}"
        youtubeLink = meal.strYoutube.toString()
        binding.jtvInstructions.text = meal.strInstructions
    }

    private fun initListener() {
        binding.swLayout.post {
            showLoading(true)
            currentMealId.let { recipeViewModel.getRecipeDetails(it) }
        }

        binding.swLayout.setOnRefreshListener {
            showLoading(true)
            currentMealId.let { recipeViewModel.getRecipeDetails(it) }
        }

        binding.toolbar.setOnClickListener {

        }
        binding.llYoutube.setOnClickListener {
            val intentYoutube = Intent(Intent.ACTION_VIEW)
            intentYoutube.data = Uri.parse(youtubeLink)
            startActivity(intentYoutube)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.swLayout.isRefreshing = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}