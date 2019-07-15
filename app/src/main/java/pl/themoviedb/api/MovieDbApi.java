package pl.themoviedb.api;

import io.reactivex.Observable;
import pl.themoviedb.model.APIResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Interfejs który definiuje endpointy API.
//Definuje parametry wejściowe oraz typ zwracanych danych.
public interface MovieDbApi {

    //adres do API  The Movie DB
    String API_URL = "https://api.themoviedb.org/";
    //klucz do The Movie DB API.
    String API_KEY = "771dbd687ee857337bbb84c7a4f29478";


    //endpointy dla filmów

    @GET("3/search/movie?api_key=" + API_KEY)
    //Dodatkowy parametr "query", które jest tekstem, który wpisał użytkownik i który ma zostać wyszukany w serwisie
    Observable<APIResponse> searchMovies(@Query("query") String query);

    @GET("3/movie/top_rated?api_key=" + API_KEY)
    Observable<APIResponse> bestMovies();

    @GET("3/movie/popular?api_key=" + API_KEY)
    Observable<APIResponse> popularMovies();

    //endpointy dla seriali

    @GET("3/search/tv?api_key=" + API_KEY)
    Observable<APIResponse> searchTVShows(@Query("query") String query);

    @GET("3/tv/top_rated?api_key=" + API_KEY)
    Observable<APIResponse> bestTVShows();

    @GET("3/tv/popular?api_key=" + API_KEY)
    Observable<APIResponse> popularTVShows();


}
