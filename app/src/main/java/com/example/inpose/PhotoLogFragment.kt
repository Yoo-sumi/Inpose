package com.example.inpose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpose.adapter.PhotoLogListAdapter
import com.example.inpose.data.Story
import com.example.inpose.data.photolog.PhotoLogRepository
import com.example.inpose.databinding.FragmentPhotologBinding

class PhotoLogFragment : Fragment(), MainContract.View {

    private lateinit var binding: FragmentPhotologBinding
    private lateinit var photoLogListAdapter: PhotoLogListAdapter
    private val presenter: MainPresenter by lazy {
        MainPresenter(
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
        presenter.loadItems()
    }

    override fun updateAdapter(storyList: List<Story>) {
        photoLogListAdapter.submitList(storyList)
    }
}