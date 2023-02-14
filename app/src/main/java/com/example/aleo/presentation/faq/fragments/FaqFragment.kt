package com.example.aleo.presentation.faq.fragments

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.SearchView
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aleo.R
import com.example.aleo.databinding.FragmentFaqBinding
import com.example.aleo.presentation.faq.model.FaqEntity
import com.example.aleo.presentation.faq.recycler.FaqRecyclerViewAdapter
import java.util.*


class FaqFragment : Fragment() {

    private lateinit var courseRV: RecyclerView
    private lateinit var binding: FragmentFaqBinding

    private lateinit var  adapter: FaqRecyclerViewAdapter
    private lateinit var faqEntityList: List<FaqEntity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaqBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = FaqFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseRV = binding.faqRecyclerView
        buildRecyclerView()
        buildSerch()
    }

    private fun buildSerch() {
        val searchView = binding.searchView
       searchView.setOnSearchClickListener {
           binding.logoPlaceHolder.visibility = View.INVISIBLE
           binding.searchView.setBackgroundResource(R.drawable.searchviewshape)
           binding.searchView.layoutParams.width = MATCH_PARENT
       }
        searchView.setOnCloseListener {
            binding.logoPlaceHolder.visibility = View.VISIBLE
            binding.searchView.setBackgroundResource(R.drawable.emptyset)
            binding.searchView.layoutParams.width = WRAP_CONTENT
            return@setOnCloseListener false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
    })}

    private fun filter(text: String) {
        val filteredlist = ArrayList<FaqEntity>()
        for (item in faqEntityList) {
            if (item.questionText.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }

    private fun buildRecyclerView() {
        faqEntityList = ArrayList<FaqEntity>()
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
                getString(R.string.question1),
                "DSA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question2),
            "JAVA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question3),
            "You can explore all the official links from the start-here channel. There is also a curated list of Aleo & Leo code and resources made by CTO Howard Wu: https://github.com/howardwu/awesome-aleo",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question4),
            "Python Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question5),
                "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question6),
                "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question7),
                "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question8),
                "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question9),
                "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question10),
            "DSA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question11),
            "JAVA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question12),
            "C++ Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question13),
            "Python Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question14),
            "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question15),
            "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question16),
            "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question17),
            "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question18),
            "Fork CPP Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question19),
            "DSA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question20),
            "JAVA Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question21),
            "C++ Self Paced Course",1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question22),
            "Python Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question23),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question24),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question25),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question26),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question27),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question28),
            "DSA Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question29),
            "JAVA Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question30),
            "C++ Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question31),
            "Python Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question32),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question33),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question34),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question35),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question36),
            "Fork CPP Self Paced Course",2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question37),
            getString(R.string.answer37),2))

        adapter = FaqRecyclerViewAdapter(requireContext(), faqEntityList)

        val manager = LinearLayoutManager(requireContext())
        courseRV.setHasFixedSize(true)

        courseRV.layoutManager = manager

        courseRV.adapter = adapter
    }

}

