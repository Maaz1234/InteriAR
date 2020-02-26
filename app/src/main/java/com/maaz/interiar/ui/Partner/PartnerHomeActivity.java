package com.maaz.interiar.ui.Partner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.maaz.interiar.R;
import com.maaz.interiar.ui.PartnerDrawer.AboutInteriARActivity;
import com.maaz.interiar.ui.PartnerDrawer.FollowersActivity;
import com.maaz.interiar.ui.PartnerDrawer.MyProductsActivity;
import com.maaz.interiar.ui.PartnerDrawer.NotificationActivity;
import com.maaz.interiar.ui.PartnerDrawer.SettingsActivity;
import com.maaz.interiar.ui.activities.ProductDetailsActivity;


import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class PartnerHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button CurrentOrderDashboardbtn, ProductsDashboardbtn, EarningsDashboardbtn, AddNewProductDashboardbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //TextView For Bottom Navigation Bar footer
        View aboutinteriar = findViewById(R.id.nav_about_interiar);
        aboutinteriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do footer action

                Intent intent = new Intent(PartnerHomeActivity.this, AboutInteriARActivity.class);
                startActivity(intent);
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

                /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner, new AboutInteriarFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);*/
            }
        });


        GraphView graph = (GraphView) findViewById(R.id.graph_view);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        CurrentOrderDashboardbtn = (Button) findViewById(R.id.btn_current_order_partner_dashboard);
        ProductsDashboardbtn = (Button) findViewById(R.id.btn_products_partner_dashboard);
        EarningsDashboardbtn = (Button) findViewById(R.id.btn_earning_partner_dashboard);
        AddNewProductDashboardbtn = (Button) findViewById(R.id.btn__add_new_product_partner_dashboard);


        CurrentOrderDashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(PartnerHomeActivity.this, "Current Order Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        ProductsDashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(PartnerHomeActivity.this, "Products Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        EarningsDashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(PartnerHomeActivity.this, "Earnings Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        AddNewProductDashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(PartnerHomeActivity.this, AddNewProductByCategoryActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.partner_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notifications) {

            Intent intent = new Intent(PartnerHomeActivity.this, NotificationActivity.class);
            startActivity(intent);
            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new NotificationFragment()).commit();*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products)
        {
            Intent intent = new Intent(PartnerHomeActivity.this, MyProductsActivity.class);
            startActivity(intent);

            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new MyProductsFragment()).commit();*/
        }

        else if (id == R.id.nav_notification)
        {
            Intent intent = new Intent(PartnerHomeActivity.this, NotificationActivity.class);
            startActivity(intent);
            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new NotificationFragment()).commit();*/
        }

//        else if (id == R.id.nav_followers)
//        {
//            Intent intent = new Intent(PartnerHomeActivity.this, FollowersActivity.class);
//            startActivity(intent);
            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new FollowersFragment()).commit();*/
//        }

//        else if (id == R.id.nav_settings)
//        {
            Intent intent = new Intent(PartnerHomeActivity.this, SettingsActivity.class);
            startActivity(intent);
            /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new SettingsFragment()).commit();*/
//        }

       /* else if (id == R.id.nav_about_interiar)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_partner,
                    new AboutInteriarFragment()).commit();
        }*/


        /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
