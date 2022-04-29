package com.minhkhue.runningtracker.utils

import android.content.Context
import android.view.LayoutInflater
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.minhkhue.runningtracker.databinding.MarkerViewBinding
import com.minhkhue.runningtracker.model.Run
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(
    private val runs: List<Run>,
    c: Context,
    layoutId: Int
) : MarkerView(c, layoutId) {
    private val binding = MarkerViewBinding.inflate(LayoutInflater.from(context), this, false)
    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        if (e == null) {
            return
        }
        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timeStamp
        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(calendar.time)

        val avgSpeed = "${run.avgSpeedInKM}km/h"
        binding.tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distancesInMeter / 1000f}km"
        binding.tvDistance.text = distanceInKm

        binding.tvDuration.text = Extensions.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}kcal"
        binding.tvCaloriesBurned.text = caloriesBurned
    }
}