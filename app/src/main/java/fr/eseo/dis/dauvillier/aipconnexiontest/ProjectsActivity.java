package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.adapters.ProjetsAdapter;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjetsDao;

public class ProjectsActivity extends  MasterActivity  {

    public static final String PROJECT_EXTRA = "project_extra";

    public static int NEW_CARD_COUNTER;

    private ProjetsAdapter projetAdapter;
    private ProjetsDao projetsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
            values.add("LIPRJ");
            values.add(login);
            values.add(token);
        MainActivity.FetchDataLogon fetchDataLogon = new MainActivity.FetchDataLogon(this,"LIPRJ",values);
        fetchDataLogon.execute();
        NEW_CARD_COUNTER = 0;
        RecyclerView recycler = (RecyclerView)findViewById(R.id.projectsList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        projetAdapter = new ProjetsAdapter(this);
        recycler.setAdapter(projetAdapter);
        loadAllProjetsData();
    }

    private void loadAllProjetsData(){
        projetAdapter.setProjets(ProjectsDatabase.getDatabase(this).projetsDao().getAllProjets());
        projetAdapter.notifyDataSetChanged();
    }

    @Override
    public void getResponse(List response){
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
        values.add("MYPRJ");
        values.add(login);
        values.add(token);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
        MainActivity.FetchDataLogon fetchDataLogon = new MainActivity.FetchDataLogon(this,"LIPRJ",values);
        fetchDataLogon.execute();
    }

    @Override
    public void getResponse1(List response){
        setContentView(R.layout.activity_projects);
        Intent intent = getIntent();
        String login=intent.getStringExtra("login");
        String token=intent.getStringExtra("token");
        List<String> values= new ArrayList<String>();
        values.add("LIJUR");
        values.add(login);
        values.add(token);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
        MainActivity.FetchDataLogon fetchDataLogon = new MainActivity.FetchDataLogon(this,"LIJUR",values);
        fetchDataLogon.execute();
    }

    public void clickProjetCard(Projets projet) {
        Intent intent = new Intent(this, ProjectDetailsActivity.class);
        intent.putExtra(PROJECT_EXTRA, projet);
        startActivity(intent);
    }
}
