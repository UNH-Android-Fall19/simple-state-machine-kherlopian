package edu.newhaven.krikorherlopian.android.sandwich

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_sandwich.view.*

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View?
        get() = itemView

    fun bind(item: Sandwich) {
        itemView.name.text = item.name
    }
}
