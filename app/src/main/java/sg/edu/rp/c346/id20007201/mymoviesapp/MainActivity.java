package sg.edu.rp.c346.id20007201.mymoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    EditText editTitle, editGenre, editYear;
    Spinner spnRating;
    Button insertBtn, showListBtn;
    ArrayList <String> alRating;
    ArrayAdapter <String> aaRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editMovieTitle);
        editGenre = findViewById(R.id.editMovieGenre);
        editYear = findViewById(R.id.editMovieYear);
        spnRating = findViewById(R.id.spinnerRating);
        insertBtn = findViewById(R.id.insertBtn);
        showListBtn = findViewById(R.id.showListBtn);

        alRating = new ArrayList<>(Arrays.asList("Select movie rating","G", "PG", "PG13", "NC16", "M18", "R21"));
        aaRating = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alRating);

        spnRating.setAdapter(aaRating);


        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spnRating.getSelectedItemPosition() != 0) {
                    String title = editTitle.getText().toString();
                    String genre = editGenre.getText().toString();
                    int year = Integer.parseInt(editYear.getText().toString());
                    String rating = spnRating.getSelectedItem().toString();

                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.modifymovie(title, genre, year, rating);
                    if (inserted_id != -1) {
                        Toast.makeText(MainActivity.this, "Insert successful",
                                Toast.LENGTH_SHORT).show();
                        editTitle.setText("");
                        editGenre.setText("");
                        editYear.setText("");
                        spnRating.setSelection(0);
                    } else {
                        Toast.makeText(MainActivity.this, "Insert unsuccessful",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);

            }
        });



    }
}