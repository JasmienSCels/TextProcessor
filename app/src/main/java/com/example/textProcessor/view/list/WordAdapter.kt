package com.example.textProcessor.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.bookwordcounter.databinding.WordViewHolderBinding
import com.example.textProcessor.models.WordUIM

internal class WordAdapter : ListAdapter<WordUIM, WordViewHolder>(
    WordDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder =
        WordViewHolder(
            WordViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}