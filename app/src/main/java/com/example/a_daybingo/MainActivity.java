package com.example.a_daybingo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a_daybingo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static ArrayList<BingoItem> bingoItems, tallyItems;
    public static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
/*
        binding.addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                startActivity(new Intent(MainActivity.this, AddItemActivity.class));
            }
        });*/

        file = createDataFile(getString(R.string.file_name));
    }

    public File createDataFile(String fileName){
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), fileName);
        if (!file.mkdirs()){
            Toast.makeText(MainActivity.this, "Directory Does Not Exist", Toast.LENGTH_LONG);
        }
        try {
            file.createNewFile();
        }catch(Exception e){e.printStackTrace();}
        return file;
    }

    public static void fillBingoList(){
        if (bingoItems == null) {
            bingoItems = new ArrayList<BingoItem>();

            bingoItems.add(new BingoItem("Big Simp",0));
            bingoItems.add(new BingoItem("Colonization",0));
            bingoItems.add(new BingoItem("Pasta",0));
            bingoItems.add(new BingoItem("MEGHANA",0));
            bingoItems.add(new BingoItem("Big Sip",0));
            bingoItems.add(new BingoItem("Telling Meghana to Eat", 0));
            bingoItems.add(new BingoItem("Testosterone", 0));
            bingoItems.add(new BingoItem("IYKYK", 0));
            bingoItems.add(new BingoItem("Bonk", 0));
            bingoItems.add(new BingoItem("Bad Timing", 0));
            bingoItems.add(new BingoItem("Comp Sci", 0));
            bingoItems.add(new BingoItem("Awkward Staring", 0));
            bingoItems.add(new BingoItem("FRONCH", 0));
            bingoItems.add(new BingoItem("Standing", 0));
            bingoItems.add(new BingoItem("Breaking Traffic Laws", 0));

            tallyItems = new ArrayList<BingoItem>();

            tallyItems.add(new BingoItem("Inappropriate Jokes", 0));
            tallyItems.add(new BingoItem("Short Jokes", 0));

        }
        /*else{
            bingoItems.clear();
        }

        try {
            JSONObject fullFileObject = readFile();
            JSONArray squares = fullFileObject.getJSONArray("squares list");
            for (int i=0; i<squares.length(); i++){
                JSONObject item = squares.getJSONObject(i);
                bingoItems.add(new BingoItem(item.getString("name"), item.getInt("times checked")));
            }
        }catch(Exception e){e.printStackTrace();}*/

    }

    public static ArrayList<BingoItem> getBingoItems(){
        fillBingoList();
        return bingoItems;
    }

    public static JSONObject readFile(){
        try {
            BufferedReader rd = new BufferedReader(new FileReader(MainActivity.file));
            String jsonText = rd.readLine();
            while ((rd.readLine()) != null) {
                jsonText += rd.readLine();
            }
            Log.d("EVERYTHING IN THE FILE", jsonText);
            return new JSONObject(jsonText);
        } catch(Exception e){e.printStackTrace();}

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}