package com.labs.robots.find.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.labs.robots.find.R;
import com.labs.robots.find.model.Task;
import com.labs.robots.find.model.User;
import com.labs.robots.find.presenter.GetTaskPresenter;
import com.labs.robots.find.utils.Pref;
import com.labs.robots.find.view.adapters.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GetTasksView {
        ProgressDialog progressDialog;
        GetTaskPresenter taskPresenter;
        @Nullable
        @BindView(R.id.rv)
        RecyclerView recyclerView;

        TasksAdapter tasksAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
      //  getSupportActionBar().setTitle("Заказы");


        taskPresenter = new GetTaskPresenter(this);

        taskPresenter.getTask(111);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Заказы");

        progressDialog = new ProgressDialog(MainActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Tasks...");
        progressDialog.show();
        if (getIntent().getSerializableExtra(Pref.EXTRA_USER) != null) {
            User userModel = (User) getIntent().getSerializableExtra(Pref.EXTRA_USER);

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            TextView navUsername = (TextView) headerView.findViewById(R.id.textViewName);
            TextView navEmail = (TextView) headerView.findViewById(R.id.textViewEmail);
            navUsername.setText(userModel.getUsername().toString());
            navEmail.setText(userModel.getEmail().toString());
        } else
        {
            Toast.makeText(getBaseContext(), "/", Toast.LENGTH_LONG).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskAdd.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        List<Task> list = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        tasksAdapter = new TasksAdapter(list, this);

        recyclerView.setAdapter(tasksAdapter);

        Pref prefManager = Pref.getInstance(MainActivity.this);



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Pref prefManager = Pref.getInstance(MainActivity.this);

        if (id == R.id.profile) {
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.logout) {
            prefManager.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.my_task) {
            Intent intent = new Intent(this, myTask.class);
            startActivity(intent);

        } else if (id == R.id.task) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onTaskSuccess(List<Task> tasks) {

         progressDialog.dismiss();
         tasksAdapter.add(0, tasks);

            Toast.makeText(getBaseContext(), "success" + tasks.get(0).getName_task(), Toast.LENGTH_LONG).show();
            //finish();

    }

    public void onTaskFailed(String error) {
        progressDialog.dismiss();
        Toast.makeText(getBaseContext(), error, Toast.LENGTH_LONG).show();


    }
}
