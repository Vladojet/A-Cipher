package com.example.aleo.presentation.education.fragments

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aleo.R
import com.example.aleo.databinding.FragmentEducationBinding
import com.example.aleo.presentation.education.clickinterface.IPerformClick
import com.example.aleo.presentation.education.entity.EducationEntity
import com.example.aleo.presentation.education.recycleradapter.RecyclerViewVideoAdapter
import com.example.aleo.presentation.utils.isOnline

class EducationFragment : Fragment(), IPerformClick {

    private lateinit var binding: FragmentEducationBinding
    private var linksList = listOf(
        EducationEntity("Educational Video 1", "1cKA-tcJdkc"),
        EducationEntity("Educational Video 2", "wsa3j74bQj4"),
        EducationEntity("Educational Video 3", "uCJuprbXJk4"),
        EducationEntity("Educational Video 4", "pPHbtzmYM6Y&t=3208s"),
        EducationEntity("Educational Video 5", "ovGZYK9bq2o")
    )

    companion object {

        fun newInstance() = EducationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEducationBinding.inflate(layoutInflater).apply { binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.videoRecycler.apply {
            adapter = RecyclerViewVideoAdapter(linksList, this@EducationFragment)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override fun onVideoItemClick(link: String) {
        if (requireContext().isOnline()) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, VideoDetails.newInstance(link))
                .addToBackStack(null)
                .commit()
        } else {
            Toast.makeText(
                requireContext(),
                "Please connect to the internet",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}