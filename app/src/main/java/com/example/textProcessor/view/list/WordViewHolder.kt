package com.example.textProcessor.view.list

import androidx.recyclerview.widget.RecyclerView
import com.example.bookwordcounter.databinding.WordViewHolderBinding
import com.example.textProcessor.models.WordUIM

class WordViewHolder(private val dataBinding: WordViewHolderBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(model: WordUIM) {
        dataBinding.wordUIM = model
    }

}