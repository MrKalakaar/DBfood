package com.example.dbfood

import android.app.Activity
import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cfitem_row.view.*

class CFitemAdapter(var context:Context,var list:ArrayList<CFitems>): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v:View=LayoutInflater.from(context).inflate(R.layout.cfitem_row,parent,false)
        return ItemHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ItemHolder).bind(list[position].name, list[position].price, list[position].photo,list[position].idd)
    }
    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(n:String,p:Double,u:String,itemI_d:Int)
        {
            itemView.cfitem_name.text=n
            itemView.cfitem_price.text=p.toString()
            Picasso.with(itemView.context).load("http://192.168.9.143/dbfood/image/restaurant/vendor1@gmail.com/foodimages/"+u).into(itemView.itempic)

            itemView.add_item_pic.setOnClickListener(){

            UserInfo.itemId=itemI_d

                var obj=QtyFragment()
                var manager=(itemView.context as Activity).fragmentManager
                obj.show(manager,"QTY"
                )

            }

        }
    }
}