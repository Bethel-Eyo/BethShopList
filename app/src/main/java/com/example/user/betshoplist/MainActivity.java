package com.example.user.betshoplist;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;


public class MainActivity extends AppCompatActivity implements MaterialTabListener {
    Toolbar toolbar;
    MaterialTabHost tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateScreen();

    }

    public void instantiateScreen(){
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        tab = (MaterialTabHost) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        setSupportActionBar(toolbar);
        /**
         * Create MyPagerAdapter, set it as adapter to viewPager
         **/
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        /*
        * set up the tab with the viewpager*/
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position){
                tab.setSelectedNavigationItem(position);
            }
        });
        for (int i=0; i< adapter.getCount();i++){
            tab.addTab(
                    tab.newTab().setText(adapter.getPageTitle(i)).setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
    }

    /**
     * Create an instance of the AddList dialog fragment and show it
     */
    public void showAddListDialog(View view){
        DialogFragment dialog = ShoppingListDialog.newInstance();
        dialog.show(MainActivity.this.getSupportFragmentManager(), "ShoppingListDialog");
    }

    public void showAddMealDialog(View view){
        DialogFragment mDialog = MealsDialog.newInstance();
        mDialog.show(MainActivity.this.getSupportFragmentManager(), "MealsDialog");
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            /*set fragment to different fragments depending on the position in viewpager*/

            switch (position){
                case 0:
                    fragment = ShoppingListFragment.newInstance();
                    break;
                case 1:
                    fragment = MealsFragment.newInstance();
                    break;
                default:
                    fragment = ShoppingListFragment.newInstance();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "ShoppingLists";
                case 1:
                    return "Meals";
                default:
                    return "ShoppingLists";
            }
        }
    }

}
