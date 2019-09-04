package com.maaz.interiar.ui.Partner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.adapters.GridAdapter;

public class AddNewProductByCategoryActivity extends AppCompatActivity {

    Button kitchenBtn, bathBtn, bedroomBtn, livingBtn, lightingBtn, furnitureBtn, homedecorBtn, outdoorBtn, storageBtn;

   /* GridView gridViewCategory;

    String[] categoriesname = { "Kitchen and Dining",
                            "Bath Products",
                            "Bedroon Products",
                            "Living Products",
                            "Lighting",
                            "Furniture",
                            "Home Decor",
                            "Outdoor Products",
                            "Storage and Organization"  };

    int[] categoriesimages = {  R.drawable.kitchen,
                                R.drawable.bath,
                                R.drawable.bedroom,
                                R.drawable.living,
                                R.drawable.ligthing,
                                R.drawable.furniture,
                                R.drawable.home_decor,
                                R.drawable.outdoor,
                                R.drawable.storage  };*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_by_category);

        /*gridViewCategory = (GridView) findViewById(R.id.gridview_Category);

        GridAdapter gridAdapter = new GridAdapter(this, categoriesname, categoriesimages);
        gridViewCategory.setAdapter(gridAdapter);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        kitchenBtn = (Button) findViewById(R.id.btn_kitchen);
        bathBtn = (Button) findViewById(R.id.btn_bath);
        bedroomBtn = (Button) findViewById(R.id.btn_bedroom);
        livingBtn = (Button) findViewById(R.id.btn_living_products);
        lightingBtn = (Button) findViewById(R.id.btn_lighting);
        furnitureBtn = (Button) findViewById(R.id.btn_furniture);
        homedecorBtn = (Button) findViewById(R.id.btn_home_decor);
        outdoorBtn = (Button) findViewById(R.id.btn_outdoor);
        storageBtn = (Button) findViewById(R.id.btn_storage);


        kitchenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Kitchen Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        bathBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Bath Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        bedroomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Bedroom Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        livingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Living Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        lightingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Lighting Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        furnitureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Furniture Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        homedecorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Home Decor Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        outdoorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "Outdoor Pressed", Toast.LENGTH_SHORT).show();
            }
        });

        storageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AddNewProductByCategoryActivity.this, "storage Pressed", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if (id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
