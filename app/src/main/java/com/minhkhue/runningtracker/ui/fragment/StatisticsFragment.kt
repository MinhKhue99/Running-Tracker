package com.minhkhue.runningtracker.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentStatisticsBinding
import com.minhkhue.runningtracker.utils.CustomMarkerView
import com.minhkhue.runningtracker.utils.Extensions
import com.minhkhue.runningtracker.viewmodel.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private val staticsViewModel: StatisticsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        setupBarChart()
    }

    private fun subscribeToObservers() {
        staticsViewModel.totalTimeRun.observe(viewLifecycleOwner) {
            it?.let {
                val totalTimeRun = Extensions.getFormattedStopWatchTime(it)
                binding.tvTotalTime.text = totalTimeRun
            }
        }

        staticsViewModel.totalDistance.observe(viewLifecycleOwner) {
            it?.let {
                val km = it / 1000f
                val totalDistance = (km * 10f).roundToInt() / 10f
                val totalDistanceString = "${totalDistance}km"
                binding.tvTotalDistance.text = totalDistanceString
            }
        }

        staticsViewModel.totalAvgSpeed.observe(viewLifecycleOwner) {
            it?.let {
                val avgSpeed = (it * 10f).roundToInt() / 10f
                val avgSpeedString = "${avgSpeed}km/h"
                binding.tvAverageSpeed.text = avgSpeedString
            }
        }
        staticsViewModel.totalCaloriesBurned.observe(viewLifecycleOwner) {
            it?.let {
                val totalCalories = "$it kcal"
                binding.tvTotalCalories.text = totalCalories
            }
        }
        staticsViewModel.runsSortedByDate.observe(viewLifecycleOwner) {
            it?.let {
                val allAVGSpeed = it.indices.map { i ->
                    BarEntry(i.toFloat(), it[i].avgSpeedInKM)
                }
                val barDataSet = BarDataSet(allAVGSpeed, "Avg Seed Over Time").apply {
                    valueTextColor = Color.WHITE
                    color = ContextCompat.getColor(requireContext(), R.color.colorAccent)
                }
                binding.barChart?.data = BarData(barDataSet)
                binding.barChart?.marker = CustomMarkerView(it.reversed(),requireContext(),R.layout.marker_view)
                binding.barChart?.invalidate()
            }
        }
    }

    private fun setupBarChart() {
        binding.barChart?.xAxis?.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        binding.barChart?.axisLeft?.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        binding.barChart?.axisRight?.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        binding.barChart?.apply {
            description.text = "Avg Speed Over Time"
            legend.isEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}