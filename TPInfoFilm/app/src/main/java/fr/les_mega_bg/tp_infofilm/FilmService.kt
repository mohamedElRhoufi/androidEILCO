package fr.les_mega_bg.tp_infofilm

import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.GET






interface FilmService {


    companion object{
        val ENDPOINT : String= "https://api.themoviedb.org/3"
    }

    @GET("/movies/popular")
    fun listPopularFilms(@Query("api_key") api_key: String="844bf1f8348566326e3d7092857ed34d",@Query("language") language:String = "en-US", @Query("page") page:Int=1): Call<List<Film>>

   // @GET("/search/repositories")
   // fun searchRepos(@Query("q") query: String?): Call<Film>



}