package com.belvin.movierecommendation
//https://api.themoviedb.org/3/movie/550?api_key=7f1e4849e09c2d6e2747d903fc0aa48e
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var tmdbApi: TMDBApi
    lateinit var retrofit: Retrofit
    var searchResultList = mutableListOf<Pair<String,String?>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchList.layoutManager = LinearLayoutManager(this)

        retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        tmdbApi = retrofit.create(TMDBApi::class.java)

        searchBtn.setOnClickListener {
            searchResultList.clear()
            if(searchTxt.text.toString().trim() != ""){
                getMovies(searchTxt.text.toString())
            }
            else{
                Toast.makeText(this, "Please enter movie name to search...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getMovies(title:String){
        var call = tmdbApi.getMoviesResults("7f1e4849e09c2d6e2747d903fc0aa48e",title)
        call.enqueue(object: Callback<SearchResult>{
            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>){

                if(!response.isSuccessful){
                    showToast("Code: ${response.code()}")
                    return
                }
                var searchResults = response.body()
                for(movie in searchResults!!.results){
                    searchResultList.add(Pair(movie.title,movie.poster_path))
                }

                searchList.adapter = SearchResultListAdapter(searchResultList)
            }

            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                showToast(t.message)
            }
        })
    }

    private fun showToast(message:String?){
        Toast.makeText(this, message!!, Toast.LENGTH_SHORT).show()
    }
}