package project.util;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class AdapterBinding {
  @BindingAdapter("image")
  public static void  Image(ImageView view,String url){
  //  Log.i("log000",url);
    Glide.with(view).load("https://api.themoviedb.org"+url).into(view);
  }
}
