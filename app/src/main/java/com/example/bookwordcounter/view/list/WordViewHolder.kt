package com.example.bookwordcounter.view.list

import androidx.recyclerview.widget.RecyclerView
import com.example.post.view.databinding.UserPostViewHolderBinding
import com.nutmeg.view.models.uim.UserPostUIM

class WordViewHolder(private val dataBinding: UserPostViewHolderBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(model: UserPostUIM) {
        dataBinding.userPost = model
    }

}