package network.aleo.aleo.presentation.faq.recycler

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import network.aleo.aleo.R
import network.aleo.aleo.databinding.FaqDiscordItemBinding
import network.aleo.aleo.databinding.FaqItemBinding
import network.aleo.aleo.presentation.faq.model.FaqEntity

class FaqRecyclerViewAdapter(
    private val context: Context,
    private var faqModelList: List<FaqEntity>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = faqModelList[position]
        when (holder.itemViewType) {
            1 -> {
                val holderUsual = holder as ViewHolder
                holderUsual.questionText.text = model.questionText
                holderUsual.answerText.text = model.answerText
                Linkify.addLinks(holderUsual.questionText, Linkify.WEB_URLS)
                Linkify.addLinks(holderUsual.answerText, Linkify.WEB_URLS)
            }
            2 -> {
                val holderDiscord = holder as ViewHolderDiscord
                val string = model.questionText.plus(context.getString(R.string.from))
                val ssb = SpannableStringBuilder(string)
                val word = "  from   "
                val start = string.indexOf(word)
                val end = start + word.length
                ssb.setSpan(
                    ForegroundColorSpan(Color.GRAY),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                ssb.setSpan(
                    ImageSpan(context, R.drawable.discord_icon_svg_blue),
                    ssb.lastIndex - 1,
                    ssb.lastIndex,
                    Spannable.SPAN_INCLUSIVE_INCLUSIVE
                )
                holderDiscord.questionText.setText(ssb, TextView.BufferType.SPANNABLE)
                holderDiscord.answerText.text = model.answerText
                Linkify.addLinks(holderDiscord.questionText, Linkify.WEB_URLS)
                Linkify.addLinks(holderDiscord.answerText, Linkify.WEB_URLS)
            }
        }
    }

    override fun getItemCount() = faqModelList.size

    inner class ViewHolder(binding: FaqItemBinding) : RecyclerView.ViewHolder(binding.root) {

        var questionText: TextView
        var answerText: TextView

        init {
            questionText = binding.questionText
            answerText = binding.answerText
        }
    }

    inner class ViewHolderDiscord(binding: FaqDiscordItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var questionText: TextView
        var answerText: TextView

        init {
            questionText = binding.questionText
            answerText = binding.answerText
        }
    }
}

