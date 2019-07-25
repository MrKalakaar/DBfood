package com.example.dbfood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cfood_list1.*

class CFoodList1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cfood_list1)

        var cat:String=intent.getStringExtra("type")

        var url="http://192.168.9.143/dbfood/mobile/getitems1.php?cuisines="+cat

        var list= ArrayList<CFitems>()
        var rq:RequestQueue=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->

        for (x  in 0..response.length()-1)
            list.add(CFitems(response.getJSONObject(x).getInt("food_id"),
                response.getJSONObject(x).getString("foodname"),
                response.getJSONObject(x).getDouble("cost"),
                response.getJSONObject(x).getString("fldimage")))

            var adp=CFitemAdapter(this,list)
            cfitems_rv.layoutManager=LinearLayoutManager(this)
            cfitems_rv.adapter=adp
        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()

        })
        rq.add(jar)
    }
}
