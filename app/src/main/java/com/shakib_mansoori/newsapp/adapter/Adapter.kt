package com.shakib_mansoori.newsapp.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.shakib_mansoori.newsapp.Listener.Listener
import com.shakib_mansoori.newsapp.Model.Articles
import com.shakib_mansoori.newsapp.R
import com.shakib_mansoori.newsapp.databinding.NewsItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class Adapter(private val listener: Listener) : RecyclerView.Adapter<Adapter.TempHolder>() {

    private var list = emptyList<Articles>()
    var requestOptions = RequestOptions()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: TempHolder, position: Int) {
        val news = list[position]


        var date = formatTime(news.publishedAt)
        holder.binding.headlineText
        holder.binding.headlineText.text = news.content
        holder.binding.textCategories.text = date

        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
        Glide.with(holder.itemView).load(news.urlToImage).centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_round_close_24)
            .apply(requestOptions)
            .into(holder.binding.imagePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false);
        return TempHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: List<Articles>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class TempHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = NewsItemBinding.bind(view)


        init {
            view.setOnClickListener {
                listener.onItemClickListener(adapterPosition)
            }
        }

    }


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun formatTime(str: String): String {


        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date: Date =
            dateFormat.parse(str)

        val formatter: DateFormat =
            SimpleDateFormat("hh:mm:aa")

        val dateStr: String = formatter.format(date)

        return dateStr
    }
}
