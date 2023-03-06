package network.aleo.aleo.presentation.education.recycleradapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import network.aleo.aleo.databinding.ItemVideoBinding
import network.aleo.aleo.presentation.education.clickinterface.IPerformClick
import network.aleo.aleo.presentation.education.entity.EducationEntity


class RecyclerViewVideoAdapter(
    private val context: Context,
    private val linksList: List<EducationEntity>,
    private val clickHandler: IPerformClick
) : RecyclerView.Adapter<RecyclerViewVideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(linksList[position])
    }

    override fun getItemCount() = linksList.size

    inner class ViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(entity: EducationEntity) {
            binding.educationNumber.text = entity.lectureName
            var thumbnail = "https://img.youtube.com/vi/"+ entity.link + "/0.jpg"
            Glide.with(context).load(thumbnail).into(binding.imageEducation)
        }

        override fun onClick(p0: View?) {
            linksList[bindingAdapterPosition].link.let(clickHandler::onVideoItemClick)
        }
    }
}