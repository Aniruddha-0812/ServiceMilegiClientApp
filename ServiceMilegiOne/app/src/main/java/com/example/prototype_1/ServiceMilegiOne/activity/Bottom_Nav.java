package com.example.prototype_1.ServiceMilegiOne.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.prototype_1.ServiceMilegiOne.R;
import com.example.prototype_1.ServiceMilegiOne.utils.ViewPager_Adapeter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottom_Nav extends AppCompatActivity {

    private BottomNavigationView mNavView;

    private ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom__nav);
        mNavView = findViewById(R.id.nav_view);
        mviewPager =findViewById(R.id.view_pager);

        mNavView.setOnNavigationItemSelectedListener(navListener);

        setUpviewPager();



    }

    @Override
    public void onBackPressed() {
        if(mviewPager.getCurrentItem() == 0) {

            super.onBackPressed();

        }else{
            mviewPager.setCurrentItem(mviewPager.getCurrentItem() - 1);
        }
    }

    private void setUpviewPager() {
        ViewPager_Adapeter pagerAdapter = new ViewPager_Adapeter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(pagerAdapter);

        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mNavView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;

                    case 1:
                        mNavView.getMenu().findItem(R.id.navigation_dashboard).setChecked(true);
                        break;

                    case 2:
                        mNavView.getMenu().findItem(R.id.navigation_notifications).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private  BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){

                case R.id.navigation_home:
                    mviewPager.setCurrentItem(0);
                    break;

                case R.id.navigation_dashboard:
                    mviewPager.setCurrentItem(1);
                    break;
                case R.id.navigation_notifications:
                    mviewPager.setCurrentItem(2);
                    break;


            }



            return  true;

        }


    };

}