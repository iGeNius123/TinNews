package com.old4g.tinnews;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;
import com.old4g.tinnews.common.ContainerFragment;
import com.old4g.tinnews.common.TinBasicActivity;
import com.old4g.tinnews.common.TinBasicFragment;

public class MainActivity extends TinBasicActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomBar;
    private TinFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //link viewpager with vew
        viewPager = findViewById(R.id.viewpager);
        adapter = new TinFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(TinFragmentPagerAdapter.FRAGMENT_NUMBER);
        //setContentView(R.layout.activity_main);

        //bottom bar
        bottomBar = findViewById(R.id.bottom_navigation);
        bottomBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        viewPager.setCurrentItem(ContainerFragment.getPositionById(item.getItemId()));
                        return true;
                    }
                }
        );
        /*
        //add click listener
        findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
                //destory main activity, no longer coming back
                //finish();
            }
        });
        */
    }

    //get fragment manager of corresponding tab
    private FragmentManager getCurrentChildFragmentManager() {
        return adapter.getItem(viewPager.getCurrentItem()).getChildFragmentManager();
    }

    @Override
    public void doFragmentTransaction(TinBasicFragment basicFragment) {
        FragmentTransaction fragmentTransaction = getCurrentChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(
                R.id.child_fragment_container,
                basicFragment,
                basicFragment.getFragmentTag()).addToBackStack(null).commit();
    }

    @Override
    protected int getLayout() {

        return R.layout.activity_main;
    }

    @Override
    public void showSnackBar(String message) {

    }
    @Override
    public void onBackPressed(){
        FragmentManager fragmentManager =  getCurrentChildFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}
