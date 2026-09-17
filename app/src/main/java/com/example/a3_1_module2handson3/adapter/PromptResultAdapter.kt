package com.example.a3_1_module2handson3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a3_1_module2handson3.R
import com.example.a3_1_module2handson3.model.PromptResult

class PromptResultAdapter :
    RecyclerView.Adapter<PromptResultAdapter.PromptResultViewHolder>() {

    private var results: List<PromptResult> = emptyList()

    fun submitList(newResults: List<PromptResult>) {
        results = newResults
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromptResultViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_prompt_result,
                parent,
                false
            )

        return PromptResultViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PromptResultViewHolder,
        position: Int
    ) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class PromptResultViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val strategyText: TextView =
            itemView.findViewById(R.id.tvStrategy)

        private val responseText: TextView =
            itemView.findViewById(R.id.tvResponse)

        fun bind(result: PromptResult) {

            strategyText.text =
                result.strategy.uppercase()

            responseText.text =
                result.response
        }
    }
}