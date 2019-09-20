package com.lambdaschool.datapersistencesprintchallenge.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import com.lambdaschool.datapersistencesprintchallenge.retrofit.ListOfMoviesCallBack
import com.lambdaschool.sprint4challenge_mymovies.model.MovieOverview

class DataBaseBuilder(context: Context) {
    companion object {

        val favoriteListOfMovies = ArrayList<FavoriteMovie>()


        fun addToFavoriteList(movie: FavoriteMovie){
            AddMovieTask().execute(movie)
        }

        fun removeToFavoriteList(movie: FavoriteMovie){
            RemoveMovieTask().execute(movie)
        }

        fun getAllFavoriteMovies(){
            GetMoviesTask().execute()
        }
    }


    val db = Room.databaseBuilder(
        context.applicationContext,
        MovieDataBase::class.java,
        "favorite-movie-list"
    ).build()




     class AddMovieTask: AsyncTask<FavoriteMovie, Void, Unit>(){
         override fun doInBackground(vararg movie: FavoriteMovie?) {
             App.createDataBase?.db?.movieDao()?.addFavMovie(movie[0]!!)
         }
     }

    class RemoveMovieTask: AsyncTask<FavoriteMovie, Void, Unit>(){
        override fun doInBackground(vararg movie: FavoriteMovie?) {
            App.createDataBase?.db?.movieDao()?.deleteFavMovie(movie[0]!!)
        }
    }

    class GetMoviesTask: AsyncTask<Void, Void, List<FavoriteMovie>>(){
        override fun doInBackground(vararg p0: Void?): List<FavoriteMovie> {
            return App.createDataBase?.db?.movieDao()?.getAllFavMovies()!!
        }

        override fun onPostExecute(result: List<FavoriteMovie>?) {
            super.onPostExecute(result)
            result?.forEach {
                favoriteListOfMovies.add(it)
                val i = 0
            }

        }

    }



}