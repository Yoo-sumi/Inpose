package com.example.inpose.ui.photolog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.R
import com.example.inpose.data.Story
import com.example.inpose.databinding.ItemStoryBinding

class PhotoLogListAdapter : RecyclerView.Adapter<PhotoLogListAdapter.StoryViewHolder>() {

    private var storyList = mutableListOf<Story>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding: ItemStoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_story,
            parent,
            false
        )
        return StoryViewHolder(binding)
    }

    override fun getItemCount(): Int = storyList.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(storyList[position])
    }

    fun addStoryList(newStoryList: List<Story>) {
        storyList = (storyList + newStoryList) as MutableList<Story>
    }

    class StoryViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.toggleButtonPhotologRecommend.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.textViewPhotologRecommend.text = binding.textViewPhotologRecommend.text.toString().toInt().plus(1).toString()
                } else {
                    binding.textViewPhotologRecommend.text = binding.textViewPhotologRecommend.text.toString().toInt().minus(1).toString()
                }
            }
        }

        fun bind(story: Story) {
            binding.story = story
        }
    }
}