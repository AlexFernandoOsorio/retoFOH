package com.financiera.ecommerceapp.features.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.domain.models.candys.CandyModel

class CandysAdapter(
    private val candys: List<CandyModel>,
    private val itemRecipeClickListener: OnRecipeClickListener
) : RecyclerView.Adapter<CandysAdapter.CandysViewHolder>() {
    fun interface OnRecipeClickListener {
        fun onRecipeClick(candys: CandyModel, position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CandysViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_recipe, parent, false)
        return CandysViewHolder(view)
    }

    override fun getItemCount(): Int = candys.size

    override fun onBindViewHolder(holder: CandysViewHolder, position: Int) {
        val candy = candys[position]
        holder.candyName.text = candy.name
        holder.candyDescription.text = candy.description
        holder.candyPrice.text = candy.price

        holder.itemView.setOnClickListener {
            itemRecipeClickListener.onRecipeClick(candy, position)
        }
    }

    class CandysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val candyName: TextView = itemView.findViewById(R.id.candy_name)
        val candyDescription: TextView = itemView.findViewById(R.id.candy_description)
        val candyPrice: TextView = itemView.findViewById(R.id.candy_price)
    }
}
