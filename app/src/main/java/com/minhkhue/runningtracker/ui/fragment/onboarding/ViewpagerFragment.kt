package com.minhkhue.runningtracker.ui.fragment.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentViewPagerBinding
import com.minhkhue.runningtracker.ui.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ViewpagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    @set:Inject
    var isFirstAppOpen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = arrayListOf(
            FirstOnBoardFragment(),
            SecondOnBoardFragment(),
            ThirdOnBoardFragment(),
            SetupFragment()
        )

        val viewPagerAdapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = viewPagerAdapter
        if (!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.viewpagerFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_viewpagerFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}