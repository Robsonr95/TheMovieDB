package pl.themoviedb.util;

import pl.themoviedb.model.Result;

// Interfejs służący do komunikacji między elementami listy (adapterem listy) a Activity
public interface OnListFragmentInteractionListener {
    void onListFragmentClickInteraction(Result song, int position);
}
