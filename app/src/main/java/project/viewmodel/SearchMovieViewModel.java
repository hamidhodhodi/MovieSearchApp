package project.viewmodel;

import android.util.Log;
import android.view.View;



import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import project.model.search_movie.MovieSearchModel;
import project.util.Webservice;

public class SearchMovieViewModel extends ViewModel {
  public String name=null;
  public MutableLiveData <String> strEror=new MutableLiveData<>();;

  //========================================================
  private MutableLiveData<MovieSearchModel> mutableLiveData=new MutableLiveData<MovieSearchModel>();
  private CompositeDisposable compositeDisposable=new CompositeDisposable();

  public MutableLiveData<MovieSearchModel> getListMovie() {
    Webservice webservice=new Webservice();
    compositeDisposable.add(webservice.ListMovieSearch(name).subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieSearchModel>() {


        @Override
        public void onSuccess(MovieSearchModel movieSearchModels) {
          mutableLiveData.setValue(movieSearchModels);
    Log.i("click000","onSuccess");

        }

        @Override
        public void onError(Throwable e) {
    Log.i("click000","error:"+e.toString());
          e.printStackTrace();
        }
      })
    );
    return mutableLiveData;
  }
  //========================================================
  public void click_Search(View view){
    Log.i("click000","set click");
    if(name==null){
      strEror.setValue("نام را وارد کنید");
    }else {
      getListMovie();
    }

  }


}
