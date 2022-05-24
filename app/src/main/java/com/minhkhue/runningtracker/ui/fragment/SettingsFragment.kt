package com.minhkhue.runningtracker.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.minhkhue.runningtracker.databinding.FragmentSettingsBinding
import com.minhkhue.runningtracker.utils.Constant.KEY_NAME
import com.minhkhue.runningtracker.utils.Constant.KEY_WEIGHT
import com.minhkhue.runningtracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        loadFieldsFromSharedPref()
//        binding.btnApplyChanges.setOnClickListener {
//            val success = applyChangesToSharedPref()
//            if(success) {
//                Snackbar.make(view, "Saved changes", Snackbar.LENGTH_LONG).show()
//            } else {
//                Snackbar.make(view, "Please fill out all the fields", Snackbar.LENGTH_LONG).show()
//            }
//        }
        setUserInfo()
    }

    private fun setUserInfo() {
        mainViewModel.getUserInfo().observe(viewLifecycleOwner){user ->
            binding.tilName.text = user[0].username
            binding.tilWeight.text = user[0].weight.toString()
            binding.tilPhone.text = user[0].phoneNumber
            Glide.with(requireContext()).load(user[0].avatar).into(binding.profileImage)
        }
    }

//    private fun loadFieldsFromSharedPref() {
//        val name = sharedPreferences.getString(KEY_NAME, "")
//        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 48f)
//        binding.etName.setText(name)
//        binding.etWeight.setText(weight.toString())
//    }
//
//    private fun applyChangesToSharedPref(): Boolean {
//        val nameText = binding.etName.text.toString()
//        val weightText = binding.etWeight.text.toString()
//        if(nameText.isEmpty() || weightText.isEmpty()) {
//            return false
//        }
//        sharedPreferences.edit()
//            .putString(KEY_NAME, nameText)
//            .putFloat(KEY_WEIGHT, weightText.toFloat())
//            .apply()
//        val toolbarText = "Let's go $nameText"
//        (requireActivity() as AppCompatActivity).supportActionBar?.title = toolbarText
//        return true
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}