package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class MovieDetails extends AppCompatActivity {

    //declaration of variables

    MovieResults.ResultsBean movie;
    public ImageLoader imageLoader;
    private ImageView movie_poster;
    private ImageView movie_backdrop;
    private TextView movie_title;
    private TextView movie_description;
    private TextView language;
    private TextView popularity;

    //super.onCreate nsavedInstanceState is called to perform the default initialization of the activity
    //setContentView R.layout.activity_movie_details sets the layout file activity_movie_details.xml as the content view for the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie_poster = findViewById(R.id.imgMovie);
        movie_backdrop = findViewById(R.id.backdrop);
        movie_title = findViewById(R.id.title_movie);
        movie_description = findViewById(R.id.description);
        language = findViewById(R.id.lan);
        popularity = findViewById(R.id.pop);

        //get the Intent
        Intent intent = getIntent();
        movie = (MovieResults.ResultsBean) intent.getSerializableExtra("movie");

        movie_title.setText(movie.getTitle());
        movie_description.setText(movie.getOverview());
        language.setText(movie.getOriginal_language());
        popularity.setText(String.valueOf(movie.getPopularity()));
        Log.d("MovieDetails", "Media type: " + movie.getPopularity());

        // Load the image using Coil image loading library
        imageLoader = Coil.imageLoader(MovieDetails.this);
        ImageRequest poster = new ImageRequest.Builder(this)
                .data(ApiInterface.POSTER_BASE_URL + movie.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                .transformations(new RoundedCornersTransformation(25))// add an error image if needed
                .target(movie_poster)
                .build();
        ImageRequest backdrop = new ImageRequest.Builder(this)
                .data(ApiInterface.BACKDROP_BASE_URL + movie.getBackdrop_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                .target(movie_backdrop)
                .build();
        imageLoader.enqueue(backdrop);
        imageLoader.enqueue(poster);

    }
}