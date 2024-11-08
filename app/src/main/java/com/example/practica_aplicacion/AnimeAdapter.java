package com.example.practica_aplicacion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    private List<Anime> animeList;
    private Context context;

    public AnimeAdapter(List<Anime> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_item, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        Anime anime = animeList.get(position);
        holder.animeTitleTextView.setText(anime.getTitle());
        Glide.with(context).load(anime.getImage_url()).into(holder.animeImageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EpisodeActivity.class);
            intent.putExtra("animeId", anime.getMal_id());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder {
        ImageView animeImageView;
        TextView animeTitleTextView;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            animeImageView = itemView.findViewById(R.id.animeImageView);
            animeTitleTextView = itemView.findViewById(R.id.animeTitleTextView);
        }
    }
}
