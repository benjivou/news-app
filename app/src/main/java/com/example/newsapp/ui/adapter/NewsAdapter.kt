package com.example.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.model.News
import com.example.newsapp.databinding.NewsAdapterBinding
import com.squareup.picasso.Picasso

/**
 * Adapter to display a list of News in a RecyclerView
 */
class NewsAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var newsList = mutableListOf<News>()

    fun setNews(news: List<News>) {
        this.newsList = news.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsAdapterBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.name.text = news.title
        Picasso.get().load(news.urlToImage).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}

class MainViewHolder(val binding: NewsAdapterBinding) : RecyclerView.ViewHolder(binding.root)