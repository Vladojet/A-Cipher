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
            getString(R.string.answer1),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question2),
            getString(R.string.answer2),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question3),
            getString(R.string.answer3),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question4),
            getString(R.string.answer4),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question5),
                getString(R.string.answer5),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question6),
                getString(R.string.answer6),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question7),
                getString(R.string.answer7),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question8),
                getString(R.string.answer8),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question9),
                getString(R.string.answer9),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question10),
            getString(R.string.answer10),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question11),
            getString(R.string.answer11),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question12),
            getString(R.string.answer12),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question13),
            getString(R.string.answer13),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question14),
            getString(R.string.answer14),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question15),
            getString(R.string.answer15),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question16),
            getString(R.string.answer16),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question17),
            getString(R.string.answer17),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question18),
            getString(R.string.answer18),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question19),
            getString(R.string.answer19),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question20),
            getString(R.string.answer20),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question21),
            getString(R.string.answer21),1))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question22),
            getString(R.string.answer22),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question23),
            getString(R.string.answer23),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question24),
            getString(R.string.answer24),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question25),
            getString(R.string.answer25),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question26),
            getString(R.string.answer26),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question27),
            getString(R.string.answer27),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question28),
            getString(R.string.answer28),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question29),
            getString(R.string.answer29),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question30),
            getString(R.string.answer30),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question31),
            getString(R.string.answer31),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question32),
            getString(R.string.answer32),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question33),
            getString(R.string.answer33),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question34),
            getString(R.string.answer34),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question35),
            getString(R.string.answer35),2))
        (faqEntityList as ArrayList<FaqEntity>).add(FaqEntity(
            getString(R.string.question36),
            getString(R.string.answer36),2))
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

