package com.example.hytaleposts.presentation.detail

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.hytaleposts.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HytalePostDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewHTML: TextView
    //val viewModel = ViewModelProviders.of(this,HytalePostViewModelFactory(slug)).get(HytalePostViewModel::class.java)
    private lateinit var imageView: ImageView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hytale_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigateToHytalePostListFragment)
        }*/

        textViewName = view.findViewById(R.id.hytale_post_detail_title)
        textViewHTML = view.findViewById(R.id.html_viewer)
        imageView = view.findViewById(R.id.hytale_post_detail_img)

        loader = view.findViewById(R.id.hytalepost_loader)
        textViewError = view.findViewById(R.id.hytalepost_error)

        val slug = arguments?.getString("HytalePostSlug") ?: ""
        val viewModel: HytalePostViewModel by viewModels { HytalePostViewModelFactory(slug) }

        viewModel.hytalePost.observe(viewLifecycleOwner, Observer { hytalePostModel ->
            loader.isVisible = hytalePostModel is HytalePostLoader
            textViewError.isVisible = hytalePostModel is HytalePostError
            if(hytalePostModel is HytalePostSuccess) {
                textViewName.text = hytalePostModel.hytalePost.title
                Glide
                    .with(view.context)
                    .load("https://cdn.hytale.com/variants/blog_thumb_"+hytalePostModel.hytalePost.coverImage.s3Key)
                    .centerCrop()
                    .into(imageView)
                val body = hytalePostModel.hytalePost.body.replace("data-src=\"","src=\"")
                displayHtml(body)
            }
        })
    }

    private fun displayHtml(html: String) {
        // Creating object of ImageGetter class you just created
        val imageGetter = ImageGetter(resources, textViewHTML)

        // Using Html framework to parse html
        val styledText =
            HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY, imageGetter,null)

        // to enable image/link clicking
        textViewHTML.movementMethod = LinkMovementMethod.getInstance()

        // setting the text after formatting html and downloading and setting images
        textViewHTML.text = styledText
    }
}