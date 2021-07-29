package com.belvin.movierecommendation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.search_result_list.view.*

class SearchResultListAdapter(var searchResults:List<Pair<String,String?>>): RecyclerView.Adapter<SearchResultListAdapter.ViewHolder>() {
    class ViewHolder(view: View,context: Context): RecyclerView.ViewHolder(view) {
        val context = context
        fun bindData(searchResult:Pair<String,String?>){
            itemView.movieName.text = searchResult.first
            if(searchResult.second != null){
                Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+searchResult.second).into(itemView.moviePoster)
            }
            else{
                itemView.moviePoster.setImageResource(R.drawable.ic_baseline_block_24)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultListAdapter.ViewHolder {
        val myView = LayoutInflater.from(parent.context).inflate(R.layout.search_result_list,parent,false)
        return ViewHolder(myView,parent.context)
    }

    override fun onBindViewHolder(holder: SearchResultListAdapter.ViewHolder, position: Int) {
        holder.bindData(searchResults[position])
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }
}