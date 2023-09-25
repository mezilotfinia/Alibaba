package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.Adapter.MyViewPagerAdapter;
import com.example.myapplication.Adapter.NavigationAdapter;
import com.example.myapplication.Fragment.BusFragment;
import com.example.myapplication.Fragment.HotelFragment;
import com.example.myapplication.Fragment.InsideFlightFragment;
import com.example.myapplication.Fragment.OutsideFlightFragment;
import com.example.myapplication.Fragment.SplashFragment;
import com.example.myapplication.Fragment.TrainFragment;
import com.example.myapplication.Model.NavigationModel;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ImageView imgHamburgerMenu;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtNavigationEmail;
    RecyclerView recyclerViewMenu;
    List<NavigationModel> models;
    SplashFragment splashFragment;
    public static FragmentManager fragmentManager;
    DrawerLayout drawer;
    NavigationAdapter navigationAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setupViews();
        setNavigationView();
        showSplashFragment();

    }


    private void setNavigationView() {
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        txtNavigationEmail.setText(email);

        models=new ArrayList<>();
        NavigationModel model=new NavigationModel();
        model.setDrawable(R.drawable.ic_profile_black_24);
        model.setTitle("پروفایل کاربری");
        NavigationModel model2=new NavigationModel();
        model2.setDrawable(R.drawable.ic_passengers_account_black);
        model2.setTitle("لیست مسافران");
        NavigationModel model3=new NavigationModel();
        model3.setDrawable(R.drawable.ic_money_black);
        model3.setTitle("سوابق تراکنش");
        NavigationModel model4=new NavigationModel();
        model4.setDrawable(R.drawable.ic_baseline_shopping_cart_black);
        model4.setTitle("خرید های من");
        NavigationModel model5=new NavigationModel();
        model5.setDrawable(R.drawable.ic_exit_to_app_black);
        model5.setTitle("خروج از حساب کاربری");
        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        navigationAdapter=new NavigationAdapter(this,models);
        recyclerViewMenu.setAdapter(navigationAdapter);
        navigationAdapter.setOnDialogDismissed(new NavigationAdapter.OnDialogDismissed() {
            @Override
            public void onDismissed(String email) {
                drawer.closeDrawer(Gravity.RIGHT);
                txtNavigationEmail.setText(email);

                sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email",email);
                editor.apply();

            }
        });
    }

    private void setupViews() {
        drawer=findViewById(R.id.drawer_main_parent);
        tabLayout=findViewById(R.id.tab_content_tab);
        viewPager=findViewById(R.id.vp_content_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new HotelFragment(),"هتل");
        myViewPagerAdapter.addFragment(new BusFragment(),"اتوبوس");
        myViewPagerAdapter.addFragment(new TrainFragment(),"قطار");
        myViewPagerAdapter.addFragment(new OutsideFlightFragment(),"پرواز خارجی");
        myViewPagerAdapter.addFragment(new InsideFlightFragment(),"پرواز داخلی");

        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(4);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_main_parent);
        txtNavigationEmail=findViewById(R.id.txt_navigation_email);
        recyclerViewMenu=findViewById(R.id.rv_navigation_menu);
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        NavigationView navigationView=findViewById(R.id.nav_view);
        //View view=navigationView.getHeaderView(0); view haye header navigation view k ye ja dig hstn
        //TextView textView=(view)findViewById(R.id.)
        navigationView.setNavigationItemSelectedListener(this);
        imgHamburgerMenu=findViewById(R.id.img_toolbar_hamburgerMenu);
        imgHamburgerMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void showSplashFragment() {
        splashFragment=new SplashFragment();
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.drawer_main_parent,splashFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(Gravity.RIGHT)){
            drawer.closeDrawer(Gravity.RIGHT);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        DrawerLayout drawer=findViewById(R.id.drawer_main_parent);
        drawer.closeDrawer(Gravity.RIGHT);
        return true;
    }


}