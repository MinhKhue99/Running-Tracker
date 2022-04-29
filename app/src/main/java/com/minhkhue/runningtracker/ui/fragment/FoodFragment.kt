package com.minhkhue.runningtracker.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentFoodBinding
import com.minhkhue.runningtracker.ui.adapter.CategoriesAdapter
import com.minhkhue.runningtracker.ui.adapter.RecommendationAdapter
import com.minhkhue.runningtracker.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initViewModel()
    }

    private fun initListener() {

    }

    private fun initViewModel() {

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

        recommendationAdapter = RecommendationAdapter()
        binding.rvRecommendation.apply {
            adapter = recommendationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
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

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}