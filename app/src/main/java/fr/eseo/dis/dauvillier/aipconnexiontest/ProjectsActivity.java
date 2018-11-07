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
    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        init();
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
        List<Projets> lProjets = ProjectsDatabase.getDatabase(this).projetsDao().getAllProjets();
        projetAdapter.setProjets(lProjets);
        projetAdapter.notifyDataSetChanged();
    }

    public void clickProjetCard(Projets projet) {
        Intent intent = new Intent(ProjectsActivity.this, ProjectDetailsActivity.class);

        intent.putExtra(PROJECT_EXTRA, projet);
        changeActivity(intent);
    }

    public void init(){

        Intent intent = getIntent();
        userName=intent.getStringExtra("userName");
        forename=intent.getStringExtra("forename");
        surname=intent.getStringExtra("surname");
        role=intent.getStringExtra("role");
        token= intent.getStringExtra("token");
        password=intent.getStringExtra("password");
    }
    public void changeActivity(Intent intent){
        intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        intent.putExtra("password",password);
        startActivity(intent);
    }

}
