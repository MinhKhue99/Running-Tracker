package com.minhkhue.runningtracker.ui.fragment.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.minhkhue.runningtracker.databinding.FragmentSecondOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondOnBoardFragment : Fragment() {
    private var _binding: FragmentSecondOnBoardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSecondOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}