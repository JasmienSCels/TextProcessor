package com.example.textProcessor.view.list

import androidx.recyclerview.widget.DiffUtil
import com.example.textProcessor.models.WordUIM

class WordDiffCallback: DiffUtil.ItemCallback<WordUIM>() {
    override fun areItemsTheSame(oldItem: WordUIM, newItem: WordUIM): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: WordUIM, newItem: WordUIM): Boolean =
        oldItem == newItem
}