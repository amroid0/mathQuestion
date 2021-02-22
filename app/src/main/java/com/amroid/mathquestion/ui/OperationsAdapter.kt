package com.amroid.mathquestion.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amroid.mathquestion.data.model.Operation
import com.amroid.mathquestion.databinding.ListOpeerationItemRowBinding
import com.amroid.mathquestion.utils.OperationDiffCallback

class OperationsAdapter : ListAdapter<Operation, OperationsAdapter.ViewHolder>(OperationDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListOpeerationItemRowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Operation) {
            binding.operation = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListOpeerationItemRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}