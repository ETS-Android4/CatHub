package com.example.taptest;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1. tablayout과 viewpager 셋팅하는 부분
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // 2. 프래그먼트 추가
        viewPagerAdapter.AddFragment(new FragmentCall(),"");
        viewPagerAdapter.AddFragment(new FragmentContact(),"");
        viewPagerAdapter.AddFragment(new TicTacFrag(),"");

        // 3. 탭레이아웃 set
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // 4. 이미지 아이콘 추가 vector asset 추가후 => android:fillColor="#fff"
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_album_wh);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_group_wh);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_star_24);

    }
}