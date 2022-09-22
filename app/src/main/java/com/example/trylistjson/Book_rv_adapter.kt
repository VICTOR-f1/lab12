package com.example.trylistjson


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Book_rv_adapter (сontext:Context? , val data:MutableList<book>): RecyclerView.Adapter<Book_rv_adapter.Book_view_Holder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(сontext)

    private var iClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Book_view_Holder {
        val view :View = layoutInflater.inflate(R.layout.item_book,parent,false)
        return  Book_view_Holder(view)
    }

    override fun onBindViewHolder(holder: Book_view_Holder, position: Int) {
        val  item= data[position]
        holder.nameTextView.text =       "Название : "+item.title
        holder.janrTextView.text =       "Жанр : "+item.genre
        holder.AuhtorTextView.text=      "Автор : "+item.author
        holder.publishingTextView.text = "Год публикации: "+item.year_of_publishing
        holder.cover_typeTextView.text = "Тип обиложки: "+item.cover_type
        holder.PAGESTextView.text=       "количество страниц: "+item.number_of_pages
    }
    override fun getItemCount(): Int =data.size

    // холдер
    inner class Book_view_Holder (iteamView: View): RecyclerView.ViewHolder(iteamView),View.OnClickListener {

        var nameTextView: TextView =iteamView.findViewById(R.id.tv_title)
        var janrTextView: TextView =iteamView.findViewById(R.id.tv_janr)
        var AuhtorTextView: TextView =iteamView.findViewById(R.id.tv_Auhtor)
        var publishingTextView: TextView =iteamView.findViewById(R.id.tv_year_of_publishing)
        var cover_typeTextView: TextView =iteamView.findViewById(R.id.tv_cover_type)
        var PAGESTextView: TextView =iteamView.findViewById(R.id.tv_NUMBER_OF_PAGES)
        init {
            iteamView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iClickListener?.onItemClick(view , adapterPosition)
        }

    }
    fun setClickListener(itemClickListener: ItemClickListener?){
        iClickListener = itemClickListener
    }

    interface ItemClickListener{
        fun onItemClick(view: View?, position: Int)
    }







}