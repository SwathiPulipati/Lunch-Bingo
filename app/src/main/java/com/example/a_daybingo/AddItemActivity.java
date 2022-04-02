package com.example.a_daybingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a_daybingo.databinding.ActivityAddItemBinding;
import com.example.a_daybingo.databinding.FragmentFirstBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddItemActivity extends Activity {
    Button enterButton;
    EditText enterItemEditText;
    ConstraintLayout constraintParentView;
    String newSquareName = "";

    private FileWriter writer;
    JSONObject fullFileObject;
    JSONArray squares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        enterButton = findViewById(R.id.enterButton);
        enterItemEditText = findViewById(R.id.enterItemEditText);
        constraintParentView = findViewById(R.id.constraintParentView);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.6));

        enterItemEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newSquareName = String.valueOf(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        try {
            writer = new FileWriter(MainActivity.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newSquareName != ""){
                    if (MainActivity.file.exists()){
                        try {
                            if (MainActivity.file.length() != 0) {
                                fullFileObject = MainActivity.readFile();
                                squares = fullFileObject.getJSONArray("squares list");

                            } else {
                                fullFileObject = new JSONObject();
                                squares = new JSONArray();
                                fullFileObject.put("squares list", squares);
                            }

                            JSONObject newSquare = new JSONObject();
                            newSquare.put("name", newSquareName);
                            newSquare.put("times checked", 0);
                            squares.put(newSquare);
                            writer.write(fullFileObject.toString());
                        }catch(Exception e){e.printStackTrace();}
                    }
                }
                finish();
            }
        });



    }


}