package com.developer35.serega.pixabayapiforandroid.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        String url = entity.getWebformatURL();

        Picasso.with(holder.thumbnailView.getContext())
                .load(url)
                .into(holder.thumbnailView);

        String authorImageUrl = entity.getUserImageURL();

        if (authorImageUrl != null && !authorImageUrl.isEmpty()) {
            Picasso.with(holder.authorImage.getContext())
                    .load(authorImageUrl)
                    .error(R.drawable.ic_person_32dp)
                    .into(holder.authorImage);
        } else {
            holder.authorImage.setImageResource(R.drawable.ic_person_32dp);
        }


        String author = holder.authorName.getContext().getString(R.string.author, items.get(position).getUserName());
        holder.authorName.setText(author);

        holder.likeView.setText(entity.getLikes());
        holder.favoriteView.setText(entity.getFavorites());
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

        ViewHolder(View itemView, AdapterClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            thumbnailView = itemView.findViewById(R.id.thumbnail_view);
            authorImage = itemView.findViewById(R.id.author_img);
            authorName = itemView.findViewById(R.id.txt_view_author);
            likeView = itemView.findViewById(R.id.txt_view_like);
            favoriteView = itemView.findViewById(R.id.txt_view_favorite);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(getAdapterPosition());
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
