package com.example.trylistjson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class  ShowBookActivity : AppCompatActivity() {
    private  lateinit var rv:RecyclerView
    private var contactList: MutableList<book> = mutableListOf()
    var index =-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_book)
        this.supportActionBar!!.hide()
        GetBooks()
        rv=findViewById(R.id.recycV)
        val adapter=Book_rv_adapter(this,contactList)
        val rvListener = object : Book_rv_adapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                index = position
                val intent = Intent(this@ShowBookActivity, AddbookActivity::class.java)
                intent.putExtra("number", position)
                startActivity(intent)
                Toast.makeText(this@ShowBookActivity, "position: $position",Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setClickListener(rvListener)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=adapter

    }

    override fun onResume() {
        super.onResume()
        if(index!=-1){
            contactList.clear()
            GetBooks()
            rv.adapter?.notifyItemChanged(index)

        }
    }
     // метод
    private fun GetBooks(){
       //  оьбект класса getSharedPreferences
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()

        }
         //
        val tempList = Gson().fromJson<List<book>>(json, object: TypeToken<List<book>>(){}.type)
        contactList.addAll(tempList)
    }




}