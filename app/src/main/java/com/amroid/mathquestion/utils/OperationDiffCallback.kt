package com.amroid.mathquestion.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.amroid.mathquestion.data.model.Operation

class OperationDiffCallback : DiffUtil.ItemCallback<Operation>() {
    override fun areItemsTheSame(oldItem: Operation, newItem: Operation): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Operation, newItem: Operation): Boolean {
        return oldItem == newItem
    }

}