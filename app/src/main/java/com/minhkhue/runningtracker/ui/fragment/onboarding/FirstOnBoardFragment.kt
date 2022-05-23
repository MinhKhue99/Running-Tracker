package com.minhkhue.runningtracker.ui.fragment.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentFirstOnBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstOnBoardFragment : Fragment() {
    private var _binding:FragmentFirstOnBoardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}