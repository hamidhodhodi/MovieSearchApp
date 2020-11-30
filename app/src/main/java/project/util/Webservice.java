package project.util;


import io.reactivex.Single;
import project.model.search_movie.MovieSearchModel;
import project.model.search_movie.detail_movie.MovieDetailModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {


 String baseUrl="https://api.themoviedb.org";
  Api api;


  public Webservice(){

    Retrofit retrofit=new Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build();
  api=  retrofit.create(Api.class);
  }
  public Single<MovieSearchModel>ListMovieSearch(String name){
    return api.ListMovieSearched(name,"1");
  }
  public Single<MovieDetailModel>MovieDetailModel(int id){
    return api.MovieDetailModel(id);
  }
}
