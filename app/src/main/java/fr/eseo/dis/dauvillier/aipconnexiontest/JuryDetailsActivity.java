package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.JuryDao;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;

public class JuryDetailsActivity extends AppCompatActivity {

    private Jury jury;

    private TextView date;

    private TextView nomProjet;

    private Button btnDetails;

    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;
    private String password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JuryDao juryDao = ProjectsDatabase.getDatabase(this).juryDao();
        setContentView(R.layout.activity_jury_details);
        int clickedJury = 0;
        Intent intent = getIntent();
        init();
        int idJury=(int)intent.getIntExtra("idJury",0);
        jury=juryDao.getJuryById(idJury);
        //nomProjet = findViewById(R.id.nom_projet);

        //btnDetails =  findViewById(R.id.button_details);
        date.setText(String.valueOf(jury.getDate()));
        /*nomProjet.setText();
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JuryDetailsActivity.this, ProjectDetailsActivity.class);
                intent.putExtra(ProjectsActivity.PROJECT_EXTRA, film);
                startActivity(intent);
            }
        });*/
        loadJuryDetails();
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



    private void loadJuryDetails() {
        System.out.println("SAMARCHE!!");
    }
}
