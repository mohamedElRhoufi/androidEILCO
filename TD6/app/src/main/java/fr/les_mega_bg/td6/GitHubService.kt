package fr.les_mega_bg.td6

import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.GET



interface GitHubService {

    companion object{
        val ENDPOINT : String= "https://api.github.com"
    }

    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<Repo>>

    @GET("/search/repositories")
    fun searchRepos(@Query("q") query: String?): Call<Repos>




}