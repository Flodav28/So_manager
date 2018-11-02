package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.adapters.JuryAdapter;
import fr.eseo.dis.dauvillier.aipconnexiontest.adapters.ProjetsAdapter;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.JuryDao;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;

public class JuryActivity extends MasterActivity {

    public static final String JURY_EXTRA = "jury_extra";

    public static int NEW_CARD_COUNTER;

    private JuryAdapter juryAdapter;
    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury);
        init();
        List<String> values1=new ArrayList<String>();
        values1.add("MYJUR");
        values1.add(userName);
        values1.add(token);
        FetchDataLogon fetchDataLIJUR = new FetchDataLogon(this, "MYJUR", values1);
        fetchDataLIJUR.execute();

    }
    public void getMyJury(List<Jury> lJury){
        NEW_CARD_COUNTER = 0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.juryList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        juryAdapter = new JuryAdapter(this);
        recycler.setAdapter(juryAdapter);
        loadAllJuryData(lJury);
    }


    private void loadAllJuryData(List<Jury> lJury){
        juryAdapter.setJury(lJury);
        juryAdapter.notifyDataSetChanged();
    }

    public void clickJuryCard(Jury jury) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(JURY_EXTRA, jury);
        changeActivity(intent);
    }

    public void init(){

        Intent intent = getIntent();
        userName=intent.getStringExtra("userName");
        forename=intent.getStringExtra("forename");
        surname=intent.getStringExtra("surname");
        role=intent.getStringExtra("role");
        token = intent.getStringExtra("token");

    }
    public void changeActivity(Intent intent){
        intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        startActivity(intent);

    }
}
