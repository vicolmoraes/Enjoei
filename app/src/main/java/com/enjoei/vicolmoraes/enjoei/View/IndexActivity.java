package com.enjoei.vicolmoraes.enjoei.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.enjoei.vicolmoraes.enjoei.R;

public class IndexActivity extends AppCompatActivity {
    private BottomNavigationView btvFooter;
    private FrameLayout flPaginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        iniciarViews();
        iniciarFooter();
        iniciarFragments(new HomeFragment());
    }

    private void iniciarViews() {
        btvFooter = findViewById(R.id.btv_index_footer);
        flPaginas = findViewById(R.id.fl_index_paginas);
    }

    private void iniciarFooter() {
        btvFooter.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.getIcon().setTint(getResources().getColor(R.color.pink_F77274));

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        iniciarFragments(new HomeFragment());
                        break;
                    case R.id.navigation_search:
                        iniciarFragments(new ProdutoFragment());
                        break;
                    case R.id.navigation_mail:
                        break;
                    case R.id.navigation_camera:
                        break;
                    case R.id.navigation_user:
                        break;
                }
                return false;
            }
        });
    }

    public void iniciarFragments(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(flPaginas.getId(), fragmento)
                .commit();
    }

}
