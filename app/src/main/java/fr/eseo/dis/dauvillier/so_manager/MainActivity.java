package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnJury;
    private Button btnProjets;
    private String forename;
    private String surname;
    private String userName;
    private String token;
    private String role;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnJury = (Button) findViewById(R.id.jury_button);
        btnJury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnJury(v);
            }
        });

        btnProjets = (Button) findViewById(R.id.projets_button);
        btnProjets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnProjets(v);
            }
        });
    }
    public void onClickBtnJury(View view){
        Intent intent = new Intent(MainActivity.this, JuryActivity.class);
        changeActivity(intent);
    }

    public void onClickBtnProjets(View view){
        Intent intent = new Intent(MainActivity.this, ProjectsActivity.class);
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
