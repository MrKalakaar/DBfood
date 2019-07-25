package com.example.dbfood

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cfooditems.*

class CFooditems : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cfooditems)

        var url="http://192.168.9.143/dbfood/mobile/getitems.php"

        var list=ArrayList<String>()
        var rq:RequestQueue=Volley.newRequestQueue(this)



        var jar=JsonArrayRequest(Request.Method.GET,url,null,Response.Listener{ response ->
            for (x in 0..response.length()-1)
                list.add(response.getJSONObject(x).getString("cuisines"))

            var adp=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
            fdlist.adapter=adp

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()

        })
        rq.add(jar)

        fdlist.setOnItemClickListener { parent, view, i, id ->

            var cat:String=list[i]
            var obj=Intent(this,CFoodList1::class.java)
            obj.putExtra("type",cat)
            startActivity(obj)

        }
    }
}
