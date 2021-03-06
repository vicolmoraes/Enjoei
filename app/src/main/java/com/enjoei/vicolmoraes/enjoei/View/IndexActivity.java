package com.enjoei.vicolmoraes.enjoei.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.enjoei.vicolmoraes.enjoei.R;
import com.enjoei.vicolmoraes.enjoei.ViewModel.BottomNavigationViewHelper;

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

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        iniciarFragments(new HomeFragment());
                        break;
                    case R.id.navigation_search:
                        iniciarFragments(new ProcurarFragment());
                        break;
                    case R.id.navigation_mail:
                        iniciarFragments(new MailFragment());
                        break;
                    case R.id.navigation_camera:
                        iniciarFragments(new CameraFragment());
                        break;
                    case R.id.navigation_user:
                        iniciarFragments(new UserFragment());
                        break;
                }
                return false;
            }
        });
        BottomNavigationViewHelper.disableShiftMode(btvFooter);
    }

    public void iniciarFragments(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(flPaginas.getId(), fragmento)
                .commit();
    }

    @Override
    public void onBackPressed() {
        iniciarFragments(new HomeFragment());
    }
}
