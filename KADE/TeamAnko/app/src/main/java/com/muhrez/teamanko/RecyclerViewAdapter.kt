package com.muhrez.teamanko

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoContext

class RecyclerViewAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ItemUi().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val name = view.findViewById<TextView>(R.id.name)
        private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: Item, listener: (Item) -> Unit) {
            name.text = items.name
            items.image?.let { Glide.with(itemView).load(items.image).into(image) }
            itemView.setOnClickListener{
                listener(items)
            }
        }
    }

    class ItemUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = ui.apply {
            relativeLayout {
                imageView {
                    id = R.id.image
                }.lparams(width = 150, height = 150) {
                    rightMargin = dip(16)
                }
                textView {
                    id = R.id.name
                    setTextSize(16F)
                }.lparams {
                    centerVertically()
                    rightOf(R.id.image)
                }
            }
        }.view
    }
}