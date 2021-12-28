package com.example.taptest;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.taptest.ui.main.SectionsPagerAdapter;
import com.example.taptest.databinding.ActivityMainBinding;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment{
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment1_layout, container, false );
    }
}
