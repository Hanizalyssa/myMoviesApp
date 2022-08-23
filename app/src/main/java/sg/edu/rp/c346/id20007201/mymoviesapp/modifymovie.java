package sg.edu.rp.c346.id20007201.mymoviesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class modifymovie {
    public class MainActivity extends AppCompatActivity {
        EditText editTitle, editGenre, editYear, editID;
        Spinner spnRating;
        Button insertBtn, showListBtn, cancelBtn;
        ArrayList<String> alRating;
        ArrayAdapter<String> aaRating;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.modify_movie);

            editTitle = findViewById(R.id.editMovieTitle);
            editGenre = findViewById(R.id.editMovieGenre);
            editID = findViewById(R.id.editMovieID);
            editYear = findViewById(R.id.editMovieYear);
            spnRating = findViewById(R.id.spinnerRating);
            insertBtn = findViewById(R.id.insertBtn);
            showListBtn = findViewById(R.id.showListBtn);
            cancelBtn = findViewById(R.id.cancelBtn);

            Intent i = getIntent();
            data = (Movie) i.getSerializableExtra("movieData");

            Intent iBack = new Intent(modifymovie.this,
                    showmovies.class);

            alRating = new ArrayList<>(Arrays.asList("G", "PG", "PG13", "NC16", "M18", "R21"));
            aaRating = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, alRating);

            spnRating.setAdapter(aaRating);

            editID.setText(String.valueOf(data.getId()));
            editTitle.setText(data.getTitle());
            editGenre.setText(data.getGenre());
            editYear.setText(String.valueOf(data.getYear()));

            for (int j = 0; j < alRating.size(); j++){
                if (alRating.get(j).equals(data.getRating())){
                    spnRating.setSelection(j);
                }
            }
            insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbh = new DBHelper(modifymovie.this);
                    data.setTitle(editTitle.getText().toString());
                    data.setGenre(editGenre.getText().toString());
                    data.setYear(Integer.parseInt(editYear.getText().toString()));
                    data.setRating(spnRating.getSelectedItem().toString());

                    dbh.updateMovie(data);
                    dbh.close();
                    startActivity(iBack);
                }
            });
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(modifymovie.this);

                    myBuilder.setTitle("Danger");
                    myBuilder.setMessage("Are you sure you want to delete the movie '"+data.getTitle()+"'");
                    myBuilder.setCancelable(false);

                    myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            DBHelper dbh = new DBHelper(modifymovie.this);
                            dbh.deleteMovie(data.getId());
                            startActivity(iBack);
                        }
                    });

                    myBuilder.setPositiveButton("Cancel", null);

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }
            });
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder myBuilder = new AlertDialog.Builder(modifymovie.this);

                    myBuilder.setTitle("Danger");
                    myBuilder.setMessage("Are you sure you want to discard the changes");
                    myBuilder.setCancelable(false);

                    myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(iBack);
                        }
                    });

                    myBuilder.setPositiveButton("Do not discard", null);

                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                }
            });
        }
    }
        }

