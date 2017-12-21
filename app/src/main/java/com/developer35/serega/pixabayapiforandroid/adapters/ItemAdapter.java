package com.developer35.serega.pixabayapiforandroid.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer35.serega.pixabayapiforandroid.R;
import com.developer35.serega.pixabayapiforandroid.entities.ItemEntity;
import com.developer35.serega.pixabayapiforandroid.interfaces.AdapterClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private final ArrayList<ItemEntity> items;
    private final AdapterClickListener clickListener;

    public ItemAdapter(ArrayList<ItemEntity> items, AdapterClickListener clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        ItemEntity entity = items.get(position);

        setThumbnailImage(entity.getWebformatURL(), holder.thumbnailView);
        setAuthorImage(entity.getUserImageURL(), holder.authorImage);

        String author = holder.authorName.getContext()
                .getString(R.string.author, items.get(position).getUserName());
        String downloads = holder.downloadsView.getContext()
                .getString(R.string.total_downloads, entity.getDownloads());
        String size = holder.sizeView.getContext()
                .getString(R.string.size, entity.getWebformatWidth(), entity.getWebformatHeight());

        holder.authorName.setText(author);
        holder.likeView.setText(entity.getLikes());
        holder.favoriteView.setText(entity.getFavorites());
        holder.downloadsView.setText(downloads);
        holder.sizeView.setText(size);
    }

    private void setThumbnailImage(String url, ImageView thumbnailView) {
        Picasso.with(thumbnailView.getContext())
                .load(url)
                .into(thumbnailView);
    }

    private void setAuthorImage(String authorImageUrl, ImageView authorImage) {
        if (authorImageUrl != null && !authorImageUrl.isEmpty()) {
            Picasso.with(authorImage.getContext())
                    .load(authorImageUrl)
                    .error(R.drawable.ic_person_32dp)
                    .into(authorImage);
        } else {
            authorImage.setImageResource(R.drawable.ic_person_32dp);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelPicassoRequest();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final AdapterClickListener clickListener;
        private final ImageView thumbnailView;
        private final ImageView authorImage;
        private final TextView authorName;
        private final TextView likeView;
        private final TextView favoriteView;
        private final TextView downloadsView;
        private final TextView sizeView;
        private final Button btnDownload;

        ViewHolder(View itemView, AdapterClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            thumbnailView = itemView.findViewById(R.id.thumbnail_view);
            authorImage = itemView.findViewById(R.id.author_img);
            authorName = itemView.findViewById(R.id.txt_view_author);
            likeView = itemView.findViewById(R.id.txt_view_like);
            favoriteView = itemView.findViewById(R.id.txt_view_favorite);
            downloadsView = itemView.findViewById(R.id.txt_view_downloads);
            sizeView = itemView.findViewById(R.id.txt_view_size);
            btnDownload = itemView.findViewById(R.id.action_download);
            thumbnailView.setOnClickListener(this);
            btnDownload.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(getAdapterPosition(), view);
        }

        private void cancelPicassoRequest() {
            Context context = thumbnailView.getContext();

            Picasso.with(context)
                    .cancelRequest(thumbnailView);

            Picasso.with(context)
                    .cancelRequest(authorImage);
        }
    }

}
