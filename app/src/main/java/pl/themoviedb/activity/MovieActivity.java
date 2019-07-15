package pl.themoviedb.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pl.themoviedb.util.Const;
import pl.themoviedb.R;
import pl.themoviedb.model.Result;

//Activity które wyświetla szczegóły filmu/serialu.
public class MovieActivity extends AppCompatActivity {

    //https://developers.themoviedb.org/3/getting-started/images - opis sposobu pobierania adresu do zdjęć.
    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/original/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);

        Result result = getIntent().getParcelableExtra(Const.RESULT);

        TextView title = findViewById(R.id.title);
        title.setText(result.getTitle());

        TextView release = findViewById(R.id.releaseDate);
        release.setText(result.getDate());

        TextView rate = findViewById(R.id.rate);
        rate.setText(String.valueOf(result.getVoteAverage()));

        //Biblioteka Glide do wyświetlenie obrazka.
        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .load(IMAGE_URL + result.getPosterPath())
                .into(image);

    }

}
