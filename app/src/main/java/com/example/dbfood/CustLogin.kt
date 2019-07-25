package com.example.dbfood

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class CustLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cust_login)

        var un=findViewById<EditText>(R.id.input_user)
        var upsw=findViewById<EditText>(R.id.input_password)

        var b1=findViewById<Button>(R.id.btn_login)
        var b2=findViewById<Button>(R.id.btn_cancel)

        b1.setOnClickListener()
        {

            var name = un.text.toString()
            var psw = upsw.text.toString()

            //http://192.168.9.143/dbfood/mobile/clogin.php?username=customer1@gmail.com&password=customer1
            var url="http://192.168.9.143/dbfood/mobile/clogin.php?username="+name+"&password="+psw
            var rq:RequestQueue=Volley.newRequestQueue(this)
            var sr=StringRequest(Request.Method.POST,url, Response.Listener { response ->
                if(response.equals("0")) {
                    Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()
                }
                else {
                   // var intent = Intent(this, MainActivity::class.java)
                    var intent = Intent(this, CFooditems::class.java)
                   // var intent = Intent(this, CFoodList1::class.java)
                    val progressDialog = ProgressDialog.show(
                        this@CustLogin, "", "Authenticating...", true
                    )
                    Toast.makeText(this, name, Toast.LENGTH_LONG).show()

                    startActivity(intent)
                    finish()
                }
            },Response.ErrorListener { error ->
              Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            })
            rq.add(sr)

//            if(name.equals("admin") && psw.equals("admin"))
//            {
//                //var preference=getSharedPreferences("pref", Context.MODE_PRIVATE)
//                //var edit=preference.edit()
//                //edit.putString("username",name)
//                //edit.commit()
//                var intent = Intent(this,MainActivity::class.java)
//                // var intent = Intent(this,FragActivity1::class.java)
//
//                var url="http://192.168.43.2/dbfood/mobile/clogin.php?eml="+name+"psw="+psw
//
//                var rq:RequestQueue =
//
//                val progressDialog = ProgressDialog.show(
//                    this@CustLogin,"","Authenticating...",true)
//                Toast.makeText(this,name, Toast.LENGTH_LONG).show()
////                R.style.Base_Theme_AppCompat_Dialog
//                startActivity(intent)
//                finish()
//
//
//            }
//            else
//            {
//                Toast.makeText(this,"fail",Toast.LENGTH_LONG).show()
//                finish()
//            }

        }

        b2.setOnClickListener()
        {
            finish()
        }
    }
}
