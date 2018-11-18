package fr.eseo.dis.dauvillier.so_manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.adapters.ProjetsAdapter;
import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;
import fr.eseo.dis.dauvillier.so_manager.data.Utilisateur;
import fr.eseo.dis.dauvillier.so_manager.data.UtilisateurDao;

public class ProjectsActivity extends  MasterActivity  {

    public static final String PROJECT_EXTRA = "project_extra";
    private  static final String MY_PREFS_NAME="sessionUser";

    public static int NEW_CARD_COUNTER;

    private ProjetsAdapter projetAdapter;
    private String forename;
    private String surname;
    private String userName;
    private String token;
    private int role;
    private String password;
    private int idUser;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
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
        if(isAccessible( idUser, projet)){
            intent.putExtra(PROJECT_EXTRA, projet);
            changeActivity(intent);
        }else{
            Toast.makeText(ProjectsActivity.this, "Ce projet est confidentiel", Toast.LENGTH_SHORT).show();
        }

    }


    public void init(){
        SharedPreferences editor = this.getSharedPreferences(MY_PREFS_NAME,0);
        userName=editor.getString("userName",null);
        forename=editor.getString("forename",null);
        surname=editor.getString("surname",null);;
        role=editor.getInt("role",1000);;
        token= editor.getString("token",null);
        password=editor.getString("password",null);
        idUser=editor.getInt("idUSer",1000);
        /*Intent intent = getIntent();
        userName=intent.getStringExtra("userName");
        forename=intent.getStringExtra("forename");
        surname=intent.getStringExtra("surname");
        role=intent.getStringExtra("role");
        token= intent.getStringExtra("token");
        password=intent.getStringExtra("password");
        idUser=intent.getIntExtra("idUser",1000);*/

    }
    public void changeActivity(Intent intent){


        startActivity(intent);

    }
    public boolean isAccessible(int idSupervisor,Projets projet){
        boolean bool=false;
        Utilisateur utilisateur=null;
        UtilisateurDao utilisateurDao = ProjectsDatabase.getDatabase(context).utilisateurDao();
        utilisateur =utilisateurDao.getUtilisateurById(idSupervisor);
        if(projet.getConfid().equals("0") || utilisateur.getIdUser()==idSupervisor){
            bool=true;
        }
        return bool;

    }

}
