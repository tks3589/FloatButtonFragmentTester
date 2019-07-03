package com.aaron.floatbuttonfragmenttester;

import android.animation.Animator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab,fab1,fab2,fab3;
    LinearLayout fab1Layout,fab2Layout,fab3Layout;
    boolean isOpen = false;
    View fabBGLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        fab1Layout = findViewById(R.id.fab1Layout);
        fab2Layout = findViewById(R.id.fab2Layout);
        fab3Layout = findViewById(R.id.fab3Layout);
        fabBGLayout = findViewById(R.id.fabBGLayout);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpen)
                    showMenu();
                else
                    closeMenu();

            }
        });
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab1.setOnClickListener(fabListener);
        fab2.setOnClickListener(fabListener);
        fab3.setOnClickListener(fabListener);

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });


    }

    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fragmentTransaction = fragmentManager.beginTransaction();
            switch (v.getId()){
                case R.id.fab1:
                    Fragment1 fragment1 = new Fragment1();
                    fragmentTransaction.replace(R.id.fragment_container,fragment1);
                    break;
                case R.id.fab2:
                    Fragment2 fragment2 = new Fragment2();
                    fragmentTransaction.replace(R.id.fragment_container,fragment2);
                    break;
                case R.id.fab3:
                    Fragment3 fragment3 = new Fragment3();
                    fragmentTransaction.replace(R.id.fragment_container,fragment3);
                    break;
            }
            fragmentTransaction.commit();
            closeMenu();
        }
    };

    private void showMenu(){
        isOpen = true;
        fab1Layout.setVisibility(View.VISIBLE);
        fab2Layout.setVisibility(View.VISIBLE);
        fab3Layout.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fab.animate().rotationBy(180).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(fab.getRotation() != 180)
                    fab.setRotation(180);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        fab1Layout.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab2Layout.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fab3Layout.animate().translationY(-getResources().getDimension(R.dimen.standard_145));


    }
    private  void closeMenu(){
        isOpen = false;
        fabBGLayout.setVisibility(View.GONE);
        fab.animate().rotationBy(-180);
        fab1Layout.animate().translationY(0);
        fab2Layout.animate().translationY(0);
        fab3Layout.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isOpen){
                    fab1Layout.setVisibility(View.GONE);
                    fab2Layout.setVisibility(View.GONE);
                    fab3Layout.setVisibility(View.GONE);
                }
                if (fab.getRotation() != -180) {
                    fab.setRotation(-180);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isOpen)
            closeMenu();
        else
            super.onBackPressed();
    }
}
