package com.example.mini_project.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mini_project.Activities.Fragments.FragmentDrawer;
import com.example.mini_project.Activities.Fragments.HomeFragment;
import com.example.mini_project.Activities.Fragments.MiniAppsFragment;
import com.example.mini_project.Activities.Fragments.ProfileFragment;
import com.example.mini_project.Activities.Fragments.System;
import com.example.mini_project.LOG_SIGN.Login;
import com.example.mini_project.R;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    public final String User_Pref = "User_Pref";
    Context context;
    Resources resources;

    private FragmentDrawer drawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        displayView(0);

    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //title
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        getSupportActionBar().setTitle(getString(R.string.MainTitle));


        //menu
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Drawer
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener((FragmentDrawer.FragmentDrawerListener) this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            Logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.Home_Title);
                break;
            case 1:
                fragment = new ProfileFragment();
                title = getString(R.string.Profile_Title);
                break;
            case 2:
                fragment = new System();
                title = getString(R.string.System);
                break;
            case 3:
                fragment = new MiniAppsFragment();
                title = getString(R.string.MiniApps_Title);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    public void Logout(){
        SharedPreferences credentials = getSharedPreferences(User_Pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = credentials.edit();
        editor.putString("NickName", null);
        editor.putString("FirstName", null);
        editor.putString("LastName", null);
        editor.putString("Email", null);
        editor.putString("Password", null);
        editor.commit();
        Intent transition = new Intent(HomeActivity.this,
                Login.class);
        HomeActivity.this.startActivity(transition);
    }
}