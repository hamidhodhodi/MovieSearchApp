package project.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import project.model.search_movie.detail_movie.MovieDetailModel;
import project.util.Webservice;

public class DetailMovieViewModel extends ViewModel {
  public String name=null;
  public MutableLiveData <String> strEror=new MutableLiveData<>();;

  //========================================================
  private MutableLiveData<MovieDetailModel> mutableLiveData=new MutableLiveData<MovieDetailModel>();
  private CompositeDisposable compositeDisposable=new CompositeDisposable();

  public MutableLiveData<MovieDetailModel> getDitail(int id) {
    Webservice webservice=new Webservice();
    compositeDisposable.add(webservice.MovieDetailModel(id).subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieDetailModel>() {


        @Override
        public void onSuccess(MovieDetailModel MovieDetailModels) {
          mutableLiveData.setValue(MovieDetailModels);
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


}
