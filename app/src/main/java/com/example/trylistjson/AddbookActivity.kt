package com.example.trylistjson

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddbookActivity : AppCompatActivity() {
   //лист который можно имзменять
    private var BookList: MutableList<book> = mutableListOf()

    private lateinit var title:EditText
    private lateinit var genre:EditText
    private lateinit var author:EditText
    private lateinit var year_of_publishing:EditText
    private lateinit var cover_type:EditText
    private lateinit var NUMBER_OF_PAGES:EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        // метод
            this.supportActionBar!!.hide()
        getBook()
        val index=intent.getIntExtra("number",-1)
        title = findViewById(R.id.editTextTexttitle)
        genre = findViewById(R.id.editTextTexjanr)
        author = findViewById(R.id.editTextTextEmailAuhtor)
        year_of_publishing = findViewById(R.id.editTextTextyear_of_publishing)
        cover_type = findViewById(R.id.editTextTextcover_type)
        NUMBER_OF_PAGES = findViewById(R.id.editTextTextNUMBER_OF_PAGES)
        button = findViewById(R.id.button3)
        Toast.makeText(this, "test: "+index.toString(), Toast.LENGTH_SHORT).show()

        if(index>-1){
            button.text="изменить"
            title.setText(BookList[index].title)
            genre.setText(BookList[index].genre)
            author.setText(BookList[index].author)
            year_of_publishing.setText(BookList[index].year_of_publishing)
            cover_type.setText(BookList[index].cover_type)
            NUMBER_OF_PAGES.setText(BookList[index].number_of_pages)

        }


        button.setOnClickListener {
            if(index==-1){
                //отправляет значения в метод
                addBook(title.text.toString(), genre.text.toString(),author.text.toString(),year_of_publishing.text.toString(), cover_type.text.toString(),NUMBER_OF_PAGES.text.toString())
                Toast.makeText(this, "значения добавлены", Toast.LENGTH_SHORT).show()

            }
            if(index>-1){
                BookList[index].title = title.text.toString()
                BookList[index].genre = genre.text.toString()
                BookList[index].author = author.text.toString()
                BookList[index].year_of_publishing = year_of_publishing.text.toString()
                BookList[index].cover_type = cover_type.text.toString()
                BookList[index].number_of_pages = NUMBER_OF_PAGES.text.toString()

                val preferences = getSharedPreferences("pref", MODE_PRIVATE)
                preferences.edit {
                    this.putString("json", Gson().toJson(BookList).toString())
                }

                Toast.makeText(this, "значения изменены", Toast.LENGTH_SHORT).show()

            }

        }
    }
    private fun getBook(){
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        var json: String = ""
        if (!preferences.contains("json")){
            return
        } else {
            json = preferences.getString("json", "NOT_JSON").toString()
        }
        val tempList = Gson().fromJson<List<book>>(json, object: TypeToken<List<book>>(){}.type)
        BookList.addAll(tempList)
    }

    private fun addBook(title:String, genre:String,author:String,year_of_publishing:String, cover_type:String,NUMBER_OF_PAGES:String){
        val contact = book(title, genre,author,year_of_publishing,cover_type,NUMBER_OF_PAGES)
        BookList.add(contact)
        val preferences = getSharedPreferences("pref", MODE_PRIVATE)
        preferences.edit {
            this.putString("json", Gson().toJson(BookList).toString())
        }
    }
}
