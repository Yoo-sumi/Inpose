package com.example.inpose.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.R
import com.example.inpose.data.Story
import com.example.inpose.databinding.ItemPhotologBinding

class PhotoLogListAdapter : ListAdapter<Story, PhotoLogListAdapter.StoryViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding: ItemPhotologBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photolog,
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StoryViewHolder(
        private val binding: ItemPhotologBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.toggleButtonPhotologRecommend.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.textViewPhotologRecommend.text = (binding.textViewPhotologRecommend.text.toString().toInt().plus(1)).toString()
                } else {
                    binding.textViewPhotologRecommend.text = (binding.textViewPhotologRecommend.text.toString().toInt().minus(1)).toString()
                }
            }
        }

        fun bind(story: Story) {
            binding.story = story
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.storyId == newItem.storyId
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }
        }
    }
}