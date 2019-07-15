package pl.themoviedb.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pl.themoviedb.R;
import pl.themoviedb.model.Result;
import pl.themoviedb.util.OnListFragmentInteractionListener;

// Klasa adaptera do wyświelania elementów na liście RecyclerView
public class ResultsRecyclerViewAdapter extends RecyclerView.Adapter<ResultsRecyclerViewAdapter.ViewHolder> {

    private final List<Result> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ResultsRecyclerViewAdapter(List<Result> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    // Metoda tworzy i zwraca ViewHolder, który przechowuje widok elementu listy.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    // Metoda ustawia wartości w widoku elementu na liście dla konkretnego elementu wg zmiennej position.
    // Ustawia również akcje listenerów kliknięcia na element listy.
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Result result = mValues.get(position);
        holder.mItem = result;
        holder.mTitle.setText(result.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public Result mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
        }

    }
}
