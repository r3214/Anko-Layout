package com.muhrez.teamanko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailActivityUI().setContentView(this)

        val itemDetail = intent.getSerializableExtra("itemDetail") as Item
        val name = findViewById<TextView>(R.id.nameDetail)
        val image = findViewById<ImageView>(R.id.imageDetail)
        val detail = findViewById<TextView>(R.id.detailText)

        name.text = itemDetail.name
        Glide.with(this)
                .load(itemDetail.image)
                .into(image)

        detail.text = itemDetail.detail
    }

    class DetailActivityUI : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = ui.apply {
            verticalLayout {
                imageView {
                    id = R.id.imageDetail
                }.lparams(width = 400, height = 400){
                    margin = dip (8)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textView {
                    id = R.id.nameDetail
                    setTextSize(24F)
                }.lparams {
                    margin = dip (8)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                textView {
                    id = R.id.detailText
                    setTextSize(12F)
                }.lparams {
                    margin = dip (16)
                    gravity = Gravity.CENTER_HORIZONTAL
                }
            }
        }.view
    }
}