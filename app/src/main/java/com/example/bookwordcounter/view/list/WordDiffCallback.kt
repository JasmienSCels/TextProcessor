package com.example.bookwordcounter.view.list

import androidx.recyclerview.widget.DiffUtil
import com.example.bookwordcounter.models.WordUIM

class WordDiffCallback: DiffUtil.ItemCallback<WordUIM>() {
    override fun areItemsTheSame(oldItem: WordUIM, newItem: WordUIM): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: WordUIM, newItem: WordUIM): Boolean =
        oldItem == newItem
}