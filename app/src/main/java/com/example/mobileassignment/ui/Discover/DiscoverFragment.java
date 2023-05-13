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

    private @NonNull FragmentDiscoverBinding binding;
    private DiscoverAdapter dAdapter;
    private RecyclerView discoverView;
    private DiscoverViewModel discoverViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        discoverView = root.findViewById(R.id.movies_list);
        setUpRecyclerView();
        fetchItems();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void fetchItems() {
        discoverViewModel.getMovies().observe(getViewLifecycleOwner(), movieList ->{dAdapter.updateMovies(movieList);});
    }
    private void setUpRecyclerView() {
        dAdapter = new DiscoverAdapter(new ArrayList<>());
        discoverView.setAdapter(dAdapter);
        discoverView.setLayoutManager(new LinearLayoutManager(discoverView.getContext()));
    }
}