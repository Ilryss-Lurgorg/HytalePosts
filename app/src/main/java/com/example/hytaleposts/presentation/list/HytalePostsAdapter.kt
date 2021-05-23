package com.example.hytaleposts.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hytaleposts.R
import com.example.hytaleposts.presentation.api.HytalePostResponse

class HytalePostsAdapter(private var dataSet: List<HytalePostResponse>, var listener: ((String) -> Unit)? = null) :
    RecyclerView.Adapter<HytalePostsAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.hytale_post_name)
            imageView = view.findViewById(R.id.hytale_post_img)
        }
    }

    fun updateList(list: List<HytalePostResponse>) {
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.hytale_post, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val hytalePost = dataSet[position]
        viewHolder.textView.text = hytalePost.title
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(hytalePost.slug)
        }

        Glide
            .with(viewHolder.itemView.context)
            .load("https://cdn.hytale.com/variants/blog_thumb_"+hytalePost.coverImage.s3Key)
            .centerCrop()
            .into(viewHolder.imageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}