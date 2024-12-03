
package com.fintech.myapplication

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fintech.myapplication.databinding.ItemPostBinding

class PostViewHolder(
    private val binding: ItemPostBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        post: Post,
        onItemClicked: (Post, ImageView) -> Unit,
    ) {
        binding.postTitle.text = post.title
        binding.postAuthor.text = post.author
        binding.imageView.load(post.imageUrl) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(post, binding.imageView)
        }
    }
}
