package pl.themoviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pl.themoviedb.MovieDbApp;
import pl.themoviedb.R;
import pl.themoviedb.model.APIResponse;
import pl.themoviedb.model.Result;
import pl.themoviedb.util.Const;

// Activity widoczne po wybraniu opcji w MainActivity. Zawiera 1 pole tekstowe do wpisania
// oraz 3 przyciski: Wyszukaj, Najwyżej oceniane oraz Popularne.
public class CategoryActivity extends AppCompatActivity {

    private String categoryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        categoryType = getIntent().getStringExtra(Const.CATEGORY);

        TextView category = findViewById(R.id.category);
        // w zależności od przekazanego typu wyświetla odpowiedni napis
        if (categoryType.equals(Const.MOVIES))
            category.setText("Filmy");
        else if (categoryType.equals(Const.TV_SHOWS))
            category.setText("Seriale");

        final EditText editText = findViewById(R.id.editText);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // sprawdzam czy wpisano jakikolwiek tekst w pole tekstowe, jeśli nie wyświetla się błąd
                if (editText.getText().length() > 0) {
                    String title = null;
                    Observable<APIResponse> observable = null;
                    // pobiera wpisaną frazę z pola tekstowego
                    String query = editText.getText().toString();
                    // w zależności od przekazanego typu ustawia odpowiedni tytuł widoczny w następnym Activity
                    // oraz przypisuje do Observable odpowiednią metodę wysyłającą zapytanie do API o filmy lub seriale
                    if (categoryType.equals(Const.MOVIES)) {
                        observable = MovieDbApp.get().getAPI().searchMovies(query);
                        title = "Wyszukiwanie filmów: " + query;
                    } else if (categoryType.equals(Const.TV_SHOWS)) {
                        observable = MovieDbApp.get().getAPI().searchTVShows(query);
                        title = "Wyszukiwanie seriali: " + query;
                    }
                    // wysyłam request do API
                    sendRequest(title, observable);
                } else {
                    // wyświetla tekst proszacy o wpisanie frazy wyszukiwania
                    Toast.makeText(CategoryActivity.this, "Wpisz tekst do wyszukania", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.best).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = null;
                Observable<APIResponse> observable = null;
                if (categoryType.equals(Const.MOVIES)) {
                    observable = MovieDbApp.get().getAPI().bestMovies();
                    title = "Najwyżej oceniane filmy";
                } else if (categoryType.equals(Const.TV_SHOWS)) {
                    observable = MovieDbApp.get().getAPI().bestTVShows();
                    title = "Najwyżej oceniane seriale";
                }
                sendRequest(title, observable);
            }
        });

        findViewById(R.id.popular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = null;
                Observable<APIResponse> observable = null;
                if (categoryType.equals(Const.MOVIES)) {
                    observable = MovieDbApp.get().getAPI().popularMovies();
                    title = "Popularne filmy";
                } else if (categoryType.equals(Const.TV_SHOWS)) {
                    observable = MovieDbApp.get().getAPI().popularTVShows();
                    title = "Popularne seriale";
                }

                sendRequest(title, observable);
            }
        });

    }

    private Consumer<Throwable> throwableConsumer = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            throwable.printStackTrace();
            Toast.makeText(CategoryActivity.this, "Wystąpił błąd podczas pobierania danych", Toast.LENGTH_SHORT).show();
        }
    };

    private void sendRequest(final String title, Observable<APIResponse> observable) {
        // Wysyła zapytanie do API
        // subscribeOn(Schedulers.io()) - operacja ma być wykonana w osobnym wątku
        // observeOn(AndroidSchedulers.mainThread()) - wynik operacji wykonanej w osobnym wątku
        // ma zostać wywołany w głównym wątku aplikacji (mainThread)
        // subscribe - podpięcie do observable, zwraca wyniki wykonania
        // odpowiedźi z API w obiekcie apiResponse.
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<APIResponse>() {
                    @Override
                    public void accept(APIResponse apiResponse) throws Exception {
                        openList(title, apiResponse.getResults());
                    }
                }, throwableConsumer);
    }

    // otwiera kolejne Activity z listą wyników oraz wcześniej utworzonym tytułem
    private void openList(String title, List<Result> results) {
        Intent intent = new Intent(CategoryActivity.this, ResultsActivity.class);
        intent.putExtra(Const.TITLE, title);
        intent.putParcelableArrayListExtra(Const.DATA_LIST, new ArrayList<Parcelable>(results));
        startActivity(intent);
    }
}
