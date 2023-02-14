package com.example.aleo.presentation.education.recycleradapter

import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.aleo.R
import com.example.aleo.databinding.ItemVideoBinding
import com.example.aleo.presentation.education.clickinterface.IPerformClick
import com.example.aleo.presentation.education.fragments.VideoDetails
import com.example.aleo.presentation.education.entity.EducationEntity
import kotlinx.coroutines.currentCoroutineContext

class RecyclerViewVideoAdapter(
    private val linksList: List<EducationEntity>,
    private val clickHandler: IPerformClick
    ) : RecyclerView.Adapter<RecyclerViewVideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvVideoNumber.text = linksList[position].lectureName
    }

    override fun getItemCount() = linksList.size

    inner class ViewHolder(binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val tvVideoNumber: TextView = binding.educationNumber

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentVideo = linksList[bindingAdapterPosition].link
            clickHandler.clickedVideoItem(currentVideo)
        }
    }
}