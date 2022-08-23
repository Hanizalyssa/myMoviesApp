package sg.edu.rp.c346.id20007201.mymoviesapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class showmovies {
    public class MainActivity extends AppCompatActivity {
        EditText editTitle, editGenre, editYear;
        Spinner spnRating;
        Button insertBtn, showListBtn;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.show_movies);

            editTitle = findViewById(R.id.editMovieTitle);
            editGenre = findViewById(R.id.editMovieGenre);
            editYear = findViewById(R.id.editMovieYear);
            spnRating = findViewById(R.id.spinnerRating);
            insertBtn = findViewById(R.id.insertBtn);
            showListBtn = findViewById(R.id.showListBtn);


                spnRating = new ArrayList<>(Arrays.asList("Filter by rating", "G", "PG", "PG13", "NC16", "M18", "R21"));
                spnRating = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, alRating);

                spnRating.setAdapter(aaRating);


                    }
                });
            }
        }
}
