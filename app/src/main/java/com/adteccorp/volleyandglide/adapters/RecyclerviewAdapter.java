package com.adteccorp.volleyandglide.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adteccorp.volleyandglide.R;
import com.adteccorp.volleyandglide.activities.AnimeActivity;
import com.adteccorp.volleyandglide.model.Anime;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    private Context context;
    List<Anime> animelist = new ArrayList<>();
    private RequestOptions option;

    public RecyclerviewAdapter(Context context, List<Anime> animelist) {
        this.context = context;
        this.animelist = animelist;
        option = new RequestOptions().centerCrop().placeholder( R.drawable.loading_shape ).error( R.drawable.loading_shape );
    }

    @NonNull
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.anim_row_item, parent, false );
        final ViewHolder viewHolder = new ViewHolder( view );
        viewHolder.view_container.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( context, AnimeActivity.class );
                i.putExtra( "anime_name", animelist.get( viewHolder.getAdapterPosition() ).getName() );
                i.putExtra( "anime_description", animelist.get( viewHolder.getAdapterPosition() ).getDescription() );
                i.putExtra( "anime_studio", animelist.get( viewHolder.getAdapterPosition() ).getDescription() );
                i.putExtra( "anime_category", animelist.get( viewHolder.getAdapterPosition() ).getCategories() );
                i.putExtra( "anime_episode", animelist.get( viewHolder.getAdapterPosition() ).getEpisode() );
                i.putExtra( "anim_rating", animelist.get( viewHolder.getAdapterPosition() ).getRating() );
                i.putExtra( "anime_img", animelist.get( viewHolder.getAdapterPosition() ).getImage_url() );
                context.startActivity( i );
            }
        } );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText( animelist.get( position ).getName() );
        holder.tv_categories.setText( animelist.get( position ).getCategories() );
        holder.tv_rating.setText( animelist.get( position ).getRating() );
        holder.tv_studio.setText( animelist.get( position ).getStudio() );

        //Load the image from the internet and set that image to the image view
        Glide.with( context ).load( animelist.get( position ).getImage_url() ).apply( option ).into( holder.iv_thumbnail );


    }

    @Override
    public int getItemCount() {
        return animelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_categories, tv_rating, tv_studio;
        private ImageView iv_thumbnail;
        LinearLayout view_container;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            tv_title = itemView.findViewById( R.id.animtitle );
            tv_categories = itemView.findViewById( R.id.animcategories );
            tv_rating = itemView.findViewById( R.id.animrating );
            tv_studio = itemView.findViewById( R.id.animstudio );
            iv_thumbnail = itemView.findViewById( R.id.thumbnail );
            view_container = itemView.findViewById( R.id.container );

        }
    }
}
