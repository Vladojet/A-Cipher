package com.example.aleo.presentation.faq.recycler

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aleo.R
import com.example.aleo.databinding.FaqDiscordItemBinding
import com.example.aleo.databinding.FaqItemBinding
import com.example.aleo.presentation.faq.model.FaqEntity
import java.lang.ref.WeakReference

class FaqRecyclerViewAdapter(
    private val context: Context,
    private var faqModelList: List<FaqEntity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    init {
        this.faqModelList = faqModelList
    }

    fun filterList(filterList: ArrayList<FaqEntity>) {
        faqModelList = filterList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (faqModelList[position].type) {
            1 -> faqModelList[position].type
            2 -> faqModelList[position].type
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> return ViewHolder(
                FaqItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> return ViewHolderDiscord(
                FaqDiscordItemBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        } //TODO UNREACHABLE CODE

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = faqModelList[position]
        when (holder.itemViewType) {
            1 -> {val holderUsual = holder as ViewHolder
                holderUsual.questionText.text = model.questionText
                holderUsual.answerText.text = model.answerText}
            2 -> {
                val holderDiscord = holder as ViewHolderDiscord
                val strFrom = context.getString(R.string.from)
                val ssb = SpannableStringBuilder(model.questionText.plus(strFrom))
                ssb.setSpan(ImageSpan(context,R.drawable.discord_icon_svg_blue), (ssb.lastIndex-1), ssb.lastIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                holderDiscord.questionText.setText(ssb, TextView.BufferType.SPANNABLE)
                holderDiscord.answerText.text = model.answerText
            }
        }

    }

    /*override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = faqModelList[position]
        holder.questionText.text = model.questionText
        holder.answerText.text = model.answerText
    }*/

    override fun getItemCount() = faqModelList.size

    inner class ViewHolder(binding: FaqItemBinding) : RecyclerView.ViewHolder(binding.root) {

        lateinit var questionText: TextView
        lateinit var answerText: TextView

        init {
            // initializing our views with their ids.
            questionText = binding.questionText
            answerText = binding.answerText
        }
    }

    inner class ViewHolderDiscord(binding: FaqDiscordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var questionText: TextView
        lateinit var answerText: TextView

        init {
            // initializing our views with their ids.
            questionText = binding.questionText
            answerText = binding.answerText
        }
    }

    inner class ViewHolderEmpty(binding: FaqDiscordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var questionText: TextView
        lateinit var answerText: TextView

        init {
            // initializing our views with their ids.
            questionText = binding.questionText
            answerText = binding.answerText
        }
    }
}

