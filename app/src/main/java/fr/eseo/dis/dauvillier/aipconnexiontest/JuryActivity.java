package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import fr.eseo.dis.dauvillier.aipconnexiontest.adapters.JuryAdapter;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.JuryDao;

public class JuryActivity extends AppCompatActivity {

    public static final String JURY_EXTRA = "jury_extra";

    public static int NEW_CARD_COUNTER;

    private JuryAdapter juryAdapter;
    private JuryDao juryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury);
        NEW_CARD_COUNTER = 0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.juryList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        juryAdapter = new JuryAdapter(this);
        recycler.setAdapter(juryAdapter);
        loadAllJuryData();
    }

    private void loadAllJuryData(){
        juryAdapter.setJury(juryDao.getAllJury());
        juryAdapter.notifyDataSetChanged();
        juryDao.getAllJury();
    }

    public void clickJuryCard(Jury jury) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(JURY_EXTRA, jury);
        startActivity(intent);
    }
}
