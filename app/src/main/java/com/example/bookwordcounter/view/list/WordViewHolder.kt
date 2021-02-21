package com.example.bookwordcounter.view.list

import androidx.recyclerview.widget.RecyclerView
import com.example.bookwordcounter.databinding.WordViewHolderBinding
import com.example.bookwordcounter.models.WordUIM

class WordViewHolder(private val dataBinding: WordViewHolderBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun onBind(model: WordUIM) {
        dataBinding.wordUIM = model
    }

}