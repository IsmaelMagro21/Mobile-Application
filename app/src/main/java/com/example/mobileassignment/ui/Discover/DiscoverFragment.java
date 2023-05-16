package com.example.mobileassignment.ui.Discover;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.R;
import com.example.mobileassignment.databinding.FragmentDiscoverBinding;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    //declaration of variables
    private @NonNull FragmentDiscoverBinding binding;
    private DiscoverAdapter dAdapter;
    private RecyclerView discoverView;
    private DiscoverViewModel discoverViewModel;

    //Creates an instance of DiscoverViewModel using the ViewModelProvider and sets up the data binding by inflating the FragmentDiscoverBinding
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        discoverView = root.findViewById(R.id.movies_list);
        setUpRecyclerView();
        fetchItems();
        return root;
    }

    //This method is overridden to clean up the binding by setting it to null when the view is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //Whenever the movie list changes, the dAdapter is updated with the new data using the updateMovies method of the adapter
    private void fetchItems() {
        discoverViewModel.getMovies().observe(getViewLifecycleOwner(), movieList ->{dAdapter.updateMovies(movieList);});
    }
    //This method configures the RecyclerView by setting up the dAdapter and LinearLayoutManager
    private void setUpRecyclerView() {
        dAdapter = new DiscoverAdapter(new ArrayList<>());
        discoverView.setAdapter(dAdapter);
        discoverView.setLayoutManager(new LinearLayoutManager(discoverView.getContext()));
    }
}