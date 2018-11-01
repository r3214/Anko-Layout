package com.muhrez.teamanko

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val detail = resources.getStringArray(R.array.club_detail)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0),
                    detail[i]))
        }

        //Recycle the typed array
        image.recycle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        val clubList = findViewById<RecyclerView>(R.id.club_list)
        initData()

        clubList.layoutManager = LinearLayoutManager(this)
        clubList.adapter = RecyclerViewAdapter(this, items){
            startActivity(intentFor<DetailActivity>("itemDetail" to it))
        }

    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
            verticalLayout{
                recyclerView {
                    id = R.id.club_list
                }.lparams(width = matchParent)
            }
        }.view
    }
}


