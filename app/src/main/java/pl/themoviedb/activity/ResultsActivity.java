package pl.themoviedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import pl.themoviedb.R;
import pl.themoviedb.adapter.ResultsRecyclerViewAdapter;
import pl.themoviedb.model.Result;
import pl.themoviedb.util.Const;
import pl.themoviedb.util.OnListFragmentInteractionListener;

//Activity które wyświetla przekazaną listę filmów/seriali oraz tytuł listy
public class ResultsActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        String title = getIntent().getStringExtra(Const.TITLE);
        List<Result> list = getIntent().getParcelableArrayListExtra(Const.DATA_LIST);

        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText(title);

        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ResultsRecyclerViewAdapter adapter = new ResultsRecyclerViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    //Po kliknięciu na element listy otwiera kolejne Activity do wyświetlenia szczegółów
    @Override
    public void onListFragmentClickInteraction(Result result, int position) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(Const.RESULT, result);
        startActivity(intent);
    }
}
