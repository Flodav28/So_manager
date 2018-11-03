package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;

public class JuryDetailsActivity extends AppCompatActivity {

    private Jury jury;

    private TextView date;
    private TextView nomProjet;

    private Button btnDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_details);
        int clickedJury = 0;
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        jury = (Jury) data.getParcelable(JuryActivity.JURY_EXTRA);
        date = findViewById(R.id.date);
        //nomProjet = findViewById(R.id.nom_projet);

        //btnDetails =  findViewById(R.id.button_details);
        date.setText(jury.getDate().toString());
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

    private void loadJuryDetails() {
        System.out.println("SAMARCHE!!");
    }
}
