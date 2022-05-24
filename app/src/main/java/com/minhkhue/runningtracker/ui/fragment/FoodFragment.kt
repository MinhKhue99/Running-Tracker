package com.minhkhue.runningtracker.ui.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentFoodBinding
import com.minhkhue.runningtracker.ui.adapter.CategoriesAdapter
import com.minhkhue.runningtracker.ui.adapter.RecommendationAdapter
import com.minhkhue.runningtracker.utils.Status
import com.minhkhue.runningtracker.viewmodel.MainViewModel
import com.minhkhue.runningtracker.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FoodFragment : Fragment() {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initListener()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initListener() {
        binding.swLayout.post {
            showLoading(true)
            recipeViewModel.getResponseMeals()
            recipeViewModel.getResponseCategories()
        }

        binding.swLayout.setOnRefreshListener {
            showLoading(true)
            recipeViewModel.getResponseMeals()
            recipeViewModel.getResponseCategories()
        }
    }

    private fun initViewModel() {
        recipeViewModel.responseMeals.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoading(true)
                    Timber.d("Loading...")
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    Timber.d("FoodRecipe:${response.data!!.meals}")
                    recommendationAdapter = RecommendationAdapter()
                    binding.rvRecommendation.apply {
                        adapter = recommendationAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                    }
                    recommendationAdapter.response = response.data.meals
                    recommendationAdapter.setOnItemClickListener {
                        val bundle = Bundle().apply {
                            putParcelable("meal", it)
                        }
                        findNavController().navigate(
                            R.id.action_foodFragment_to_detailFoodFragment,
                            bundle
                        )
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
        recipeViewModel.responseCategories.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoading(true)
                }
                Status.SUCCESS -> {
                    showLoading(false)
                    Timber.d("FoodCategory: ${response.data!!.categories}")
                    categoriesAdapter = CategoriesAdapter()
                    binding.rvCategories.apply {
                        adapter = categoriesAdapter
                        layoutManager =
                            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                        setHasFixedSize(true)
                    }
                    categoriesAdapter.response = response.data.categories
                    categoriesAdapter.setOnItemClickListener {
                        val bundle = Bundle().apply {
                            putParcelable("category", it)
                        }
                        findNavController().navigate(
                            R.id.action_foodFragment_to_filterRecipesFragment,
                            bundle
                        )
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

    private fun initView() {
        val myColor: Int = Color.parseColor("#1B8045")
        binding.swLayout.setColorSchemeColors(myColor)

        binding.toolbarHome.collapseToolbar.setExpandedTitleColor(Color.argb(0, 0, 0, 0))
        binding.toolbarHome.collapseToolbar.setContentScrimColor(
            resources.getColor(
                R.color.white
            )
        )
        mainViewModel.getUserInfo().observe(viewLifecycleOwner) { user ->
            binding.toolbarHome.textView.text =
                StringBuilder("Hello, ").append(user[0].username).append("!")
        }

    }

    private fun showLoading(state: Boolean) {
        binding.swLayout.isRefreshing = state

        if (state) {

            binding.shimmerRecommendation.startShimmerAnimation()
            binding.shimmerCategories.startShimmerAnimation()

        } else {

            binding.shimmerRecommendation.stopShimmerAnimation()
            binding.shimmerCategories.stopShimmerAnimation()
            binding.shimmerRecommendation.visibility = View.GONE
            binding.shimmerCategories.visibility = View.GONE

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}