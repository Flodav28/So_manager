package fr.eseo.dis.dauvillier.so_manager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.adapters.JuryAdapter;
import fr.eseo.dis.dauvillier.so_manager.adapters.ProjetsAdapter;
import fr.eseo.dis.dauvillier.so_manager.adapters.TitreProjetAdapter;
import fr.eseo.dis.dauvillier.so_manager.data.Eleves;
import fr.eseo.dis.dauvillier.so_manager.data.Jury;
import fr.eseo.dis.dauvillier.so_manager.data.JuryDao;
import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;
import fr.eseo.dis.dauvillier.so_manager.data.ProjetsDao;
import fr.eseo.dis.dauvillier.so_manager.data.Utilisateur;
import fr.eseo.dis.dauvillier.so_manager.data.UtilisateurDao;

public class JuryDetailsActivity extends MasterActivity {

    private  static final String MY_PREFS_NAME="sessionUser";
    public static final String PROJECT_EXTRA = "project_extra";

    private Jury jury;

    private TextView date;

    private TextView titre;

    private Button btnDetails;
    private Context context;

    public static int NEW_CARD_COUNTER;

    private String forename;
    private String surname;
    private String userName;
    private String token;
    private int role;
    private String password;
    private int idUser;

    private TitreProjetAdapter titreProjetAdapter;

    List<String> values1;
    List<Projets> projetsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projetsList = null;
        JuryDao juryDao = ProjectsDatabase.getDatabase(this).juryDao();
        setContentView(R.layout.activity_jury_details);
        Intent intent = getIntent();
        init();
        int idJury=(int)intent.getIntExtra("idJury",0);
        jury=juryDao.getJuryById(idJury);
        projetsList=getProjets(idJury);
        date = findViewById(R.id.date);
        date.setText(String.valueOf(jury.getDate()));

        RecyclerView recycler = (RecyclerView) findViewById(R.id.projetsList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        titreProjetAdapter = new TitreProjetAdapter(this);
        recycler.setAdapter(titreProjetAdapter);
        titreProjetAdapter.setProjets(projetsList);
        titreProjetAdapter.notifyDataSetChanged();

        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juory_details);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        jury = (Jury) data.getParcelable(JuryActivity.JURY_EXTRA);
        titre = findViewById(R.id.titre);
        date = findViewById(R.id.date);*/
        //note = findViewById(R.id.notation);

        //nomProjet = findViewById(R.id.nom_projet);

        //btnDetails =  findViewById(R.id.button_details);

        /*nomProjet.setText();*/
    }

    public void clickProjectCard(Projets projet) {
        Intent intent = new Intent(JuryDetailsActivity.this, ProjectDetailsActivity.class);
        if(isAccessible( idUser, projet)){
            intent.putExtra(PROJECT_EXTRA, projet);
            intent.putExtra("id_projet", projet.getIdProject());
            changeActivity(intent);
        }else{
            Toast.makeText(JuryDetailsActivity.this, "Ce projet est confidentiel", Toast.LENGTH_SHORT).show();
        }
    }

    public void init() {
        SharedPreferences editor = this.getSharedPreferences(MY_PREFS_NAME, 0);
        userName = editor.getString("userName", null);
        forename = editor.getString("forename", null);
        surname = editor.getString("surname", null);
        ;
        role = editor.getInt("role", 1000);
        ;
        token = editor.getString("token", null);
        password = editor.getString("password", null);
        idUser = editor.getInt("idUSer", 1000);
    }

    public void getMyProjects(String result){
        if("OK".equals(result)) {
            RecyclerView recycler = (RecyclerView) findViewById(R.id.juryList);
            recycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(llm);
        }else{
            values1 = new ArrayList<>();
            values1.add("LOGON");
            values1.add(userName);
            values1.add(password);
            FetchDataLogon fetchDataLIJUR = new FetchDataLogon(this, "LOGON", values1);
            fetchDataLIJUR.execute();
        }
    }

    public void changeActivity(Intent intent){

        startActivity(intent);
    }
    public List<Projets > getProjets(int idJury){
        List<Projets> lProjets;
        lProjets = new ArrayList<>();
        ProjetsDao projetsDao = ProjectsDatabase.getDatabase(this).projetsDao();
        lProjets=projetsDao.getProjetsByIdJury(idJury);
        return lProjets;
    }

  /*  private void loadJuryDetails() {
        List<String> values=new ArrayList<String>();
        values.add("JYINF");
        values.add(userName);
        values.add(String.valueOf(jury.getIdJury()));
        values.add(token);
        FetchDataLogon fetchDataJYINF= new FetchDataLogon(this, "JYINF", values);
        fetchDataJYINF.execute();
        List<String> result=new ArrayList<String>();
        result = new FetchDataLogon.getVariableList("JYINF");
        System.out.println("RESULTAT : " + fetchDataJYINF.data);
    }*/

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