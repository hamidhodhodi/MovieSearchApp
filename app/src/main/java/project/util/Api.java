package project.util;



import java.util.List;

import io.reactivex.Single;
import project.model.search_movie.MovieSearchModel;
import project.model.search_movie.detail_movie.MovieDetailModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

  @GET("/3/search/movie?api_key=426896529c48e2511b2d9ff745f74f9e")
  Single<MovieSearchModel>ListMovieSearched(@Query("query") String name,@Query("page") String page);
  @GET("/3/movie/{id}?api_key=426896529c48e2511b2d9ff745f74f9e")
  Single<MovieDetailModel>MovieDetailModel(@Path("id") int id);



}
