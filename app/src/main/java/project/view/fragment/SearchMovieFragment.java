package project.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hodhodi.hamid.moviesearchapp.R;
import hodhodi.hamid.moviesearchapp.databinding.FragmentSearchMovieBinding;
import project.model.search_movie.MovieSearchModel;
import project.model.search_movie.Result;
import project.util.ClickItem;
import project.view.adapter.AdapterMovieList;
import project.viewmodel.SearchMovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchMovieFragment extends Fragment implements ClickItem {
  /**
   *this fragment show detail of movie.
   *
   * @author  Hamid Hodhodi
   * @version 1.0
   * @since   2020-30-10
   */
FragmentSearchMovieBinding binding;
  private MutableLiveData<MovieSearchModel> mutableLiveData;
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;
  private RecyclerView rcvMovie;
  private AdapterMovieList adapter;

  public SearchMovieFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment SearchMovieFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static SearchMovieFragment newInstance(String param1, String param2) {
    SearchMovieFragment fragment = new SearchMovieFragment();
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
    binding= DataBindingUtil.inflate(inflater, R.layout.fragment_search_movie,container,false);
    // Inflate the layout for this fragment
    rcvMovie = binding.rcvMovie;
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SearchMovieViewModel searchMovieViewModel= ViewModelProviders.of(this).get(SearchMovieViewModel.class);
    binding.setDataname(searchMovieViewModel);
    mutableLiveData=searchMovieViewModel.getListMovie();
mutableLiveData.observe((LifecycleOwner) this, new Observer<MovieSearchModel>() {
      @Override
      public void onChanged(MovieSearchModel musicListModels) {
        Log.i("log0003","list"+musicListModels.toString());
        rcvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Result> data = musicListModels.getResults();
        adapter = new AdapterMovieList(data, SearchMovieFragment.this::clickItem);
        rcvMovie.setAdapter(adapter);


      }
    });

  }

  @Override
  public void clickItem(Result dataModel) {
    Bundle bundle=new Bundle();
    bundle.putInt("id",dataModel.getId());
    Navigation.findNavController(rcvMovie).navigate(R.id.action_searchMovieFragment_to_ditailFragment,bundle);

  }
}