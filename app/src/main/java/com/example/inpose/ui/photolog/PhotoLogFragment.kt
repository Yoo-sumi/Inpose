package com.example.inpose.ui.photolog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.inpose.ui.photolog.adapter.PhotoLogListAdapter
import com.example.inpose.data.Story
import com.example.inpose.data.photolog.PhotoLogRepository
import com.example.inpose.databinding.FragmentPhotologBinding

class PhotoLogFragment : Fragment(), PhotoLogContract.View {

    private lateinit var binding: FragmentPhotologBinding
    private lateinit var photoLogListAdapter: PhotoLogListAdapter
    private val presenter: PhotoLogPresenter by lazy {
        PhotoLogPresenter(
            view = this@PhotoLogFragment,
            photoLogRepository = PhotoLogRepository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotologBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoLogListAdapter = PhotoLogListAdapter()
        binding.recyclerViewPhotolog.adapter = photoLogListAdapter
        presenter.loadItems(photoLogListAdapter.itemCount)
        initScrollListener()
    }

    override fun updateAdapter(storyList: List<Story>) {
        val itemCount = photoLogListAdapter.itemCount
        photoLogListAdapter.addStoryList(storyList)
        photoLogListAdapter.notifyItemChanged(itemCount - 1, itemCount - 1 + 20)
    }

    private fun initScrollListener(){
        binding.recyclerViewPhotolog.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if ((recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition() == photoLogListAdapter.itemCount - 5){
                    moreItems()
                }
            }
        })
    }

    private fun moreItems() {
        presenter.loadItems(photoLogListAdapter.itemCount / 20)
    }
}