package com.alfi.test.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.alfi.test.R;
import com.alfi.test.adapter.AuctionAdapter;
import com.alfi.test.model.Auction;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private AuctionAdapter auctionAdapter;
    private HomeViewModel homeViewModel;
    private ProgressBar progressbar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);

        auctionAdapter = new AuctionAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(auctionAdapter);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        progressbar = view.findViewById(R.id.progressbar);
        homeViewModel.showLoading(true, progressbar);

        homeViewModel.getAuction().observe(getViewLifecycleOwner(), getAuction);
        homeViewModel.setAuction("EXTRA_MOVIE");
    }

    private Observer<ArrayList<Auction>> getAuction = new Observer<ArrayList<Auction>>() {
        @Override
        public void onChanged(ArrayList<Auction> auctions) {
            if (auctions != null){
                auctionAdapter.setDataAuction(auctions);
            }
            homeViewModel.showLoading(false, progressbar);
        }
    };
}