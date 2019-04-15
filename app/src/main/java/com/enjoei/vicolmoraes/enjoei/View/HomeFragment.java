package com.enjoei.vicolmoraes.enjoei.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enjoei.vicolmoraes.enjoei.R;

public class HomeFragment extends Fragment {
    private RecyclerView rvProdutos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        iniciarViews(view);
        return view;
    }

    private void iniciarViews(View view) {
        rvProdutos = view.findViewById(R.id.rv_home_produtos);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
    }
}
