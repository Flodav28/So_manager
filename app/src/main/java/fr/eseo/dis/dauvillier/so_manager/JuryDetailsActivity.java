package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.adapters.JuryAdapter;
import fr.eseo.dis.dauvillier.so_manager.adapters.ProjetsAdapter;
import fr.eseo.dis.dauvillier.so_manager.data.Jury;
import fr.eseo.dis.dauvillier.so_manager.data.JuryDao;
import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;
import fr.eseo.dis.dauvillier.so_manager.data.ProjetsDao;

public class JuryDetailsActivity extends MasterActivity {

    private Jury jury;

    private TextView date;

    private TextView titre;

    private Button btnDetails;

    public static int NEW_CARD_COUNTER;

    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;
    private String password;

    private ProjetsAdapter projetsAdapter;

    List<String> values1;
    List<Projets> projetsList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projetsList = null;
        JuryDao juryDao = ProjectsDatabase.getDatabase(this).juryDao();
        setContentView(R.layout.activity_jury_details);
        int clickedJury = 0;
        Intent intent = getIntent();
        init();
       // loadJuryDetails();
        int idJury=(int)intent.getIntExtra("idJury",0);
        jury=juryDao.getJuryById(idJury);
        date = findViewById(R.id.date);
        titre = findViewById(R.id.titre);
        date.setText(String.valueOf(jury.getDate()));
        projetsList=getProjets(idJury);


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
        /*btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JuryDetailsActivity.this, ProjectDetailsActivity.class);
                //intent.putExtra(ProjectsActivity.PROJECT_EXTRA, film);
                changeActivity(intent);
            }
        });*/
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
        intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        intent.putExtra("password",password);
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
}