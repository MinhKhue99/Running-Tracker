package com.minhkhue.runningtracker.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentFilterRecipesBinding
import com.minhkhue.runningtracker.model.remote.Category
import com.minhkhue.runningtracker.ui.adapter.FilteredRecipesAdapter
import com.minhkhue.runningtracker.utils.Status
import com.minhkhue.runningtracker.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FilterRecipesFragment : Fragment() {
    private var _binding: FragmentFilterRecipesBinding? = null
    private val binding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    private val args: FilterRecipesFragmentArgs by navArgs()
    private lateinit var category: Category
    private lateinit var filteredRecipesAdapter: FilteredRecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFilterRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category = args.category
        initViewModel()
        initView()
        initListener()
    }

    private fun initView() {
        binding.toolbarMenu.tvTitle.text = category.strCategory
    }

    private fun initViewModel() {
        recipeViewModel.responseMealsByCategory.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> {
                    Timber.d("Loading...")
                }
                Status.SUCCESS -> {
                    Timber.d("FoodRecipe:${response.data!!.meals}")
                    filteredRecipesAdapter = FilteredRecipesAdapter()
                    binding.rvRecipes.apply {
                        adapter = filteredRecipesAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                    }
                    filteredRecipesAdapter.response = response.data.meals
                    filteredRecipesAdapter.setOnItemClickListener {
                        val bundle = Bundle().apply {
                            putParcelable("meal", it)
                        }
                        findNavController().navigate(
                            R.id.action_filterRecipesFragment_to_detailFoodFragment,
                            bundle
                        )
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initListener() {
        binding.toolbarMenu.ibBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_filterRecipesFragment_to_foodFragment)
        }
        category.strCategory?.let { recipeViewModel.getAllMealByCategory(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}