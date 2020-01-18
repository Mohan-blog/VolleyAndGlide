package com.adteccorp.volleyandglide.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.adteccorp.volleyandglide.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_anime );
        //hide the default actionbar
        getSupportActionBar().hide();

        //Receiving the data
        String name = getIntent().getExtras().getString( "anime_name" );
        String description = getIntent().getExtras().getString( "anime_description" );
        String studio = getIntent().getExtras().getString( "anime_studio" );
        String category = getIntent().getExtras().getString( "anime_category" );
        String episode = getIntent().getExtras().getString( "anime_episode" );
        String image = getIntent().getExtras().getString( "anime_img" );
        String rating =getIntent().getExtras().getString( "anim_rating" );


        //init views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById( R.id.collapsingtoolbarid );
        collapsingToolbarLayout.setTitleEnabled( true );

        TextView tv_name = findViewById( R.id.aa_animtitle );
        TextView tv_studio =findViewById(R.id.aa_animstudio);
        TextView tv_catogories =findViewById( R.id.aa_animcategories );
        TextView tv_description = findViewById( R.id.aa_description );
        TextView tv_rating =findViewById( R.id.aa_animrating );
        ImageView img = findViewById( R.id.aa_thumbnail );


        //setting values to each view

        tv_name.setText( name );
        tv_studio.setText( studio );
        tv_catogories.setText( category );
        tv_description.setText( description );
        tv_rating.setText( rating );

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder( R.drawable.loading_shape ).error( R.drawable.loading_shape );



        //set image using glide

        Glide.with(this).load( image ).apply( requestOptions ).into( img );

    }
}
