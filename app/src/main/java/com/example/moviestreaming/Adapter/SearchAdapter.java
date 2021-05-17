package com.example.moviestreaming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviestreaming.Activity.MovieDetailActivity;
import com.example.moviestreaming.Model.MovieModel;
import com.example.moviestreaming.R;
import com.example.moviestreaming.Utils.Constant;
import com.example.moviestreaming.databinding.ItemsMoviesBinding;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    List<MovieModel> list;
    boolean isLimited;
    private final int limit = 10;
    SearchItemOnClick searchItemOnClick;
    public SearchAdapter(Context context, List<MovieModel> list, boolean isLimited,SearchItemOnClick searchItemOnClick){
        this.context=context;
        this.list=list;
        this.isLimited=isLimited;
        this.searchItemOnClick=searchItemOnClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsMoviesBinding binding= DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.items_movies,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MovieModel model=list.get(position);

        holder.binding.movieName.setText(model.getName());
        holder.binding.movieGenre.setText(model.getGenre());
        holder.binding.tvRate.setText(model.getRate());

        Glide.with(context).load(Constant.MAIN_URL+ Constant.CONTENT+"movies/"+list.get(position).getImage_name())
                .into(holder.binding.imgMovie);

        PushDownAnim.setPushDownAnimTo(holder.itemView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItemOnClick.onItemClick(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list.size() > limit && isLimited){
            return limit;
        }
        else
        {
            return list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemsMoviesBinding binding;
        public ViewHolder(@NonNull ItemsMoviesBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public interface SearchItemOnClick{

        void onItemClick(MovieModel model);


    }
}
