package project.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import hodhodi.hamid.moviesearchapp.R;
import hodhodi.hamid.moviesearchapp.databinding.AdapterMovieBinding;
import project.model.search_movie.Result;
import project.util.ClickItem;

public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.viewhodler> {
  private List<Result> list;
  private ClickItem clickItem;
 public AdapterMovieList(List<Result> list, ClickItem clickItem){
    this.clickItem=clickItem;
   this.list=list;
  }
  class viewhodler extends RecyclerView.ViewHolder{
    AdapterMovieBinding binding;

    public viewhodler(@NonNull AdapterMovieBinding binding) {
      super(binding.getRoot());
      this.binding=binding;
    }
  }

  @NonNull
  @Override
  public viewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new AdapterMovieList.viewhodler( DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_movie,parent,false));
  }


  @Override
  public void onBindViewHolder(@NonNull viewhodler holder, int position) {
    final Result dataList=list.get(position);
    holder.binding.setData(dataList);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        clickItem.clickItem(dataList);
      }
    });


  }

  @Override
  public int getItemCount() {
    return list.size();
  }


}
