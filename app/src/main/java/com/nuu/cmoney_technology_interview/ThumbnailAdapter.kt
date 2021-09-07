package com.nuu.cmoney_technology_interview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nuu.cmoney_technology_interview.databinding.ListItemThumbnailBinding
import com.nuu.cmoney_technology_interview.model.ThumbnailInfo
import com.nuu.cmoney_technology_interview.viewmodel.ThumbnailViewModel


class ThumbnailAdapter(private val viewModel: ThumbnailViewModel, private val context: Context): RecyclerView.Adapter<ThumbnailAdapter.ThumbnailHolder>() {
    private var thumbInfoArray: Array<ThumbnailInfo>? = null

    fun updateThumbInfoAllArray(list: Array<ThumbnailInfo>){
        thumbInfoArray = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThumbnailAdapter.ThumbnailHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemThumbnailBinding.inflate(layoutInflater, parent, false)
        return ThumbnailHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbnailAdapter.ThumbnailHolder, position: Int) {
        if (!thumbInfoArray.isNullOrEmpty()) {
            holder.bind(viewModel, thumbInfoArray!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (thumbInfoArray == null) 0 else thumbInfoArray!!.size
    }

    override fun onViewRecycled(holder: ThumbnailHolder) {
        super.onViewRecycled(holder)
        holder.binding.imvThumbnail.imageUtils.future.cancel(true)
    }


    inner class ThumbnailHolder(val binding: ListItemThumbnailBinding): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(viewModel: ThumbnailViewModel, data: ThumbnailInfo?){
            binding.viewModel = viewModel
            binding.item = data
            binding.executePendingBindings()

            binding.constMain.setOnClickListener {
                val intent = Intent(context, ThumbnailDetailActivity::class.java)
                intent.putExtra("ThumbnailInfo", data)
                context.startActivity(intent)
            }
        }
    }
}
