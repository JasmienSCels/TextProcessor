package com.example.bookwordcounter.view.list

import androidx.recyclerview.widget.DiffUtil
import com.nutmeg.view.models.uim.UserPostUIM

class WordDiffCallback: DiffUtil.ItemCallback<UserPostUIM>() {
    override fun areItemsTheSame(oldItem: UserPostUIM, newItem: UserPostUIM): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: UserPostUIM, newItem: UserPostUIM): Boolean =
        oldItem == newItem
}