package com.example.bookwordcounter.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.post.view.databinding.UserPostViewHolderBinding
import com.nutmeg.view.models.uim.UserPostUIM

internal class WordAdapter : ListAdapter<UserPostUIM, WordViewHolder>(WordDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
       WordViewHolder(
           UserPostViewHolderBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false
       ))

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}