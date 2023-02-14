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

class EducationFragment : Fragment(), IPerformClick {

    private lateinit var binding: FragmentEducationBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewVideoAdapter: RecyclerViewVideoAdapter
    private var linksList = ArrayList<EducationEntity>()

    companion object {
        @JvmStatic
        fun newInstance() = EducationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.videoRecycler

        recyclerViewVideoAdapter = RecyclerViewVideoAdapter(linksList, this)

        linearLayoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recycler.layoutManager = linearLayoutManager
        recycler.adapter = recyclerViewVideoAdapter

        prepareVideoList()
    }

    private fun prepareVideoList() {
        var entity = EducationEntity("Educational Video 1", "1cKA-tcJdkc")
        linksList.add(entity)
        entity = EducationEntity("Educational Video 2", "wsa3j74bQj4")
        linksList.add(entity)
        entity = EducationEntity("Educational Video 3", "uCJuprbXJk4")
        linksList.add(entity)
        entity = EducationEntity("Educational Video 4", "pPHbtzmYM6Y&t=3208s")
        linksList.add(entity)
        entity = EducationEntity("Educational Video 5", "ovGZYK9bq2o")
        linksList.add(entity)
    }

    override fun clickedVideoItem(string: String) {
        if (isOnline(requireContext())){
        parentFragmentManager.beginTransaction().replace(
            R.id.fragmentHolder,
            VideoDetails.newInstance(string)).addToBackStack(null).commit()
        } else
            Toast.makeText(requireContext(), "Please connect to the internet", Toast.LENGTH_LONG)
            .show()
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}