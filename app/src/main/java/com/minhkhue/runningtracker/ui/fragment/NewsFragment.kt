package com.minhkhue.runningtracker.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentNewsBinding
import com.minhkhue.runningtracker.ui.adapter.NewsAdapter
import com.minhkhue.runningtracker.utils.Status
import com.minhkhue.runningtracker.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        newsViewModel.news.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    Timber.d("News:" +
                            "${response.data!!.articles}\n")
                    setupRecyclerView()
                    newsAdapter.response = response.data.articles
                    newsAdapter.setOnItemClickListener {
                        val bundle = Bundle().apply {
                            putSerializable("article", it)
                        }
                        Timber.d("NewsItem: $it")
                        findNavController().navigate(
                            R.id.action_newsFragment_to_articleFragment,
                            bundle
                        )
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                    response.message.let {
                        Toast.makeText(
                            requireContext(),
                            "An error: $it",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(){
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
    private fun hideLoading() {
        binding.loading.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}