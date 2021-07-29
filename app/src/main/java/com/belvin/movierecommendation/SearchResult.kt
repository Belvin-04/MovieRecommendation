package com.belvin.movierecommendation

data class SearchResult(
    var page:Int,
    var results:List<Movie>,
    var total_results:Int,
    var total_pages:Int
    )