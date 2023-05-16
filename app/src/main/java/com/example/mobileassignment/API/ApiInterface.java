package com.example.mobileassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //API Key to access API
    String API_KEY = "9f514cf61fa17e951146f7c97600bd4b";

    //API URL
    String BASE_URL = "https://api.themoviedb.org";

    //Base URL used to retrieve Poster
    String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/";

    //Base URL used to retrieve Backdrop
    String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    //retrieves one page worth of movies
    int PAGE = 1;

    //Used to retrieve movies in english
    String LANGUAGE = "en-US";

    //Used to retrieve movies from the popular category
    String CATEGORY = "popular";

    static void setPage(int i) {
        i = PAGE;
    }

    //https://api.themoviedb.org/3/trending/all/day?api_key=9f514cf61fa17e951146f7c97600bd4b&language=en&page=1

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
