package com.example.hytaleposts.presentation.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hytaleposts.R
import com.example.hytaleposts.presentation.detail.HytalePostViewModelFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HytalePostListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = HytalePostsAdapter(listOf(), ::onClickedHytalePost)
    private val viewModel: HytalePostListViewModel by viewModels()
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hytale_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.hytalepost_recyclerview)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HytalePostListFragment.adapter
        }

        loader = view.findViewById(R.id.hytalepost_loader)
        textViewError = view.findViewById(R.id.hytalepost_error)

        viewModel.hytaleListPost.observe(viewLifecycleOwner, Observer { hytalePostListModel ->
            loader.isVisible = hytalePostListModel is HytalePostListLoader
            textViewError.isVisible = hytalePostListModel is HytalePostListError
            if(hytalePostListModel is HytalePostListSuccess) {
                adapter.updateList(hytalePostListModel.hytalePostList)
            }
        })
    }

    private fun onClickedHytalePost(slug: String) {
        findNavController().navigate(
            R.id.navigateToHytalePostDetailFragment, bundleOf(
                "HytalePostSlug" to slug
            )
        )
    }
}