package com.minhkhue.runningtracker.ui.fragment.onboarding

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.minhkhue.runningtracker.R
import com.minhkhue.runningtracker.databinding.FragmentSetupBinding
import com.minhkhue.runningtracker.model.local.User
import com.minhkhue.runningtracker.utils.Constant.KEY_FIRST_TIME_TOGGLE
import com.minhkhue.runningtracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment() {

    private var _binding: FragmentSetupBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
   private lateinit var imageUri:Uri

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileImage.setOnClickListener {
            openGallery()
        }
        binding.tvContinue.setOnClickListener {
            val success = saveUser()
            if (success) {
                findNavController().navigate(R.id.action_viewpagerFragment_to_runFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(Intent.createChooser(intent, "Choose Avatar"), 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when {
            requestCode == 1 && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data!!
                binding.profileImage.setImageURI(imageUri)
            }
        }
    }

    private fun saveUser():Boolean {
        val userName = binding.etName.text.toString()
        val weight = binding.etWeight.text.toString()
        val phoneNumber = binding.etPhone.text.toString()
        val avatar = imageUri.toString()
        if (userName.isEmpty()|| weight.isEmpty()||phoneNumber.isEmpty()){
            return false
        }
        mainViewModel.insertUser(User(0, userName, weight.toInt(), phoneNumber, avatar))
        sharedPref.edit()
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}