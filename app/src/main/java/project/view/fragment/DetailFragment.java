package project.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import hodhodi.hamid.moviesearchapp.R;
import hodhodi.hamid.moviesearchapp.databinding.FragmentDitailBinding;
import project.model.search_movie.detail_movie.MovieDetailModel;
import project.viewmodel.DetailMovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
  /**
   *this fragment show detail of movie.
   *
   * @author  Hamid Hodhodi
   * @version 1.0
   * @since   2020-30-10
   */
  private MutableLiveData<MovieDetailModel> mutableLiveData=new MutableLiveData<MovieDetailModel>();

FragmentDitailBinding binding;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private int id;

  public DetailFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment DitailFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static DetailFragment newInstance(String param1, String param2) {
    DetailFragment fragment = new DetailFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding= DataBindingUtil.inflate(inflater, R.layout.fragment_ditail,container,false);
id=getArguments().getInt("id");
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    DetailMovieViewModel searchMovieViewModel= ViewModelProviders.of(this).get(DetailMovieViewModel.class);
    mutableLiveData=searchMovieViewModel.getDitail(id);
    mutableLiveData.observe((LifecycleOwner) this, new Observer<MovieDetailModel>() {
      @Override
      public void onChanged(MovieDetailModel movieDetailModel) {
        binding.prbLoading.setVisibility(View.GONE);
    binding.setData2(movieDetailModel);

      }
    });
  }
}