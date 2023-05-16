package com.example.mobileassignment.ui.Discover;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.MovieDetails;
import com.example.mobileassignment.R;

import java.io.Serializable;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    private List<MovieResults.ResultsBean> movies;

    //Contains a list of MovieResults.ResultsBean objects to hold the movie data
    public DiscoverAdapter(List<MovieResults.ResultsBean> movies) {
        this.movies = movies;
    }

    //Inflates the layout_discovercard.xml layout file to create the view for each movie item
    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View discoverView = inflater.inflate(R.layout.layout_discovercard, parent, false);
        return new DiscoverAdapter.ViewHolder(discoverView);
    }

    //Responsible for binding the data to the views of each item in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
        MovieResults.ResultsBean mv = movies.get(position);
        TextView primaryTextView = holder.primaryTextView;
        primaryTextView.setText(mv.getTitle());
        TextView rating = holder.rating;
        double voteAverage = mv.getVote_average();
        rating.setText(String.valueOf(voteAverage));
        TextView date = holder.date ;
        date.setText(mv.getRelease_date());

        ImageRequest request = new ImageRequest.Builder(holder.itemView.getContext())
                .data(ApiInterface.POSTER_BASE_URL + mv.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                //.transformations(new RoundedCornersTransformation(25))// add an error image if needed
                .target(holder.posterImage)
                .build();
        holder.imageLoader.enqueue(request);
    }

    //returns size of list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //declaration of variables
        public TextView primaryTextView;
        public TextView rating;
        public TextView date;
        public ImageView posterImage;
        public ImageLoader imageLoader;

        public Button unwatchedButton;



//Sets up click listeners for both the movie card and the "unwatchedButton" button.
        public ViewHolder(final View discoverView) {
            super(discoverView);
            primaryTextView = discoverView.findViewById(R.id.movie_title);
            rating = discoverView.findViewById(R.id.movie_rate);
            date = discoverView.findViewById(R.id.movie_release);
            posterImage = discoverView.findViewById(R.id.movie_poster);
            imageLoader = Coil.imageLoader(discoverView.getContext());
            unwatchedButton = discoverView.findViewById(R.id.unwatched_button);

            //Movie Card's Click Function
            discoverView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the selected movie
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());

                    // Create an Intent to launch the MovieDetails activity
                    Intent intent = new Intent(v.getContext(), MovieDetails.class);
                    intent.putExtra("movie", (Serializable) selectedMovie);

                    // Launch the activity
                    v.getContext().startActivity(intent);
                }
            });
            //Add to List Click Function
            unwatchedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());
                    //Checking the correct Movie ID is logged when clicked
                    String id = String.valueOf(+selectedMovie.getId());
                    Log.d("Movie ID:", id);
                    String message;
                    if(!selectedMovie.getIsInList()){
                        //Add to List + Change to Orange
                        unwatchedButton.setText("Unwatched");
                        unwatchedButton.setBackgroundColor(Color.RED);
                        message = ("Have not watched " +selectedMovie.getTitle());
                        selectedMovie.setInList(true);
                        unwatchedButton.setVisibility(View.VISIBLE);
                    }
                    else{
                        unwatchedButton.setText("Watched");
                        unwatchedButton.setBackgroundColor(0xFF388E3C);
                        message = ("Watched " +selectedMovie.getTitle());
                        selectedMovie.setInList(false);
                        unwatchedButton.setVisibility(View.VISIBLE);
                    }
                    Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //Clears the existing movie list, adds all the items from the new list
    public void updateMovies (List<MovieResults.ResultsBean> newMovies){
        movies.clear();
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }
}
