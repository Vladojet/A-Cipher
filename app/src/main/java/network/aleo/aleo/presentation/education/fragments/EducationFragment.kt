package network.aleo.aleo.presentation.education.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import network.aleo.aleo.R
import network.aleo.aleo.databinding.FragmentEducationBinding
import network.aleo.aleo.presentation.education.clickinterface.IPerformClick
import network.aleo.aleo.presentation.education.entity.EducationEntity
import network.aleo.aleo.presentation.education.recycleradapter.RecyclerViewVideoAdapter
import network.aleo.aleo.presentation.utils.isOnline

class EducationFragment : Fragment(), IPerformClick {

    private lateinit var binding: FragmentEducationBinding
    private var linksList = listOf(
        EducationEntity("WHAT REASONS MAKE THE ALEO PROJECT THE MOST PROMISING IN 2023?", "a-j9aJlwL_U"),
        EducationEntity("WHAT DO YOU NEED TO DO TO BECOME AN ALEO AMBASSADOR?", "ImSNgzNs6dU"),
        EducationEntity("ALEO PROJECT ROADMAP DETAILS", "KMZ3HEqj_jE"),
        EducationEntity("FEBRUARY NEWS FROM ALEO", "KJDpFMCiatE"),
        EducationEntity("HISTORY OF INVESTMENTS IN ALEO", "K9Kzugv3Cvc")
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
            adapter = RecyclerViewVideoAdapter(requireContext(),linksList, this@EducationFragment)
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