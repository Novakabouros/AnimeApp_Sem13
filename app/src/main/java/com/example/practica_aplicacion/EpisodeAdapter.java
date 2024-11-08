package com.example.practica_aplicacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {
    private List<Episode> episodeList;
    private Context context;

    public EpisodeAdapter(List<Episode> episodeList, Context context) {
        this.episodeList = episodeList;
        this.context = context;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        holder.episodeTitleTextView.setText(episode.getTitle());
        Glide.with(context).load(episode.getImage_url()).into(holder.episodeImageView);
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        TextView episodeTitleTextView;
        ImageView episodeImageView;

        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
            episodeTitleTextView = itemView.findViewById(R.id.episodeTitleTextView);
            episodeImageView = itemView.findViewById(R.id.episodeImageView);
        }
    }
}
