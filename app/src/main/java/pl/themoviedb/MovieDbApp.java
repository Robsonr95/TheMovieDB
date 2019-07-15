package pl.themoviedb;

import android.app.Application;

import com.google.gson.Gson;

import pl.themoviedb.api.MovieDbApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//Główna klasa aplikacji, inicjalizacja biblioteki retrofit do zapytań API.
public class MovieDbApp extends Application {

    private static MovieDbApp instance;
    private MovieDbApi api;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    //metoda zwraca instancję aplikacji aby pobrać obiekt MobieDbApp.
    public static MovieDbApp get() {
        return instance;
    }

    //Do wykonywania zapytań do API używana jest biblioteka Retrofit. Poniższa metoda tworzy obiekt API.
    private MovieDbApi createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                //dodanie adaptera, który pozwoli na użycie RxJavy do wykonywania zapytań.
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //ustawienie adresu API serwisu The Movie DB.
                .baseUrl(MovieDbApi.API_URL)
                .build();

        //tworzenie obiektu API na podstawie intefejsu.
        return retrofit.create(MovieDbApi.class);
    }

    //Zwrócenie obiektu do zapytań API.
    public MovieDbApi getAPI() {
        if (api == null) {
            api = createAPI();
        }
        return api;
    }
}
