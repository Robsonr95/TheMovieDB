package pl.themoviedb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.themoviedb.R;
import pl.themoviedb.util.Const;

// Główne Activity uruchamiane po starcie aplikacji, zawiera 2 przyciski, które otwierają kolejne Activity.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.movies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra(Const.CATEGORY, Const.MOVIES);
                startActivity(intent);
            }
        });

        findViewById(R.id.tvShows).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                intent.putExtra(Const.CATEGORY, Const.TV_SHOWS);
                startActivity(intent);
            }
        });

    }
}
