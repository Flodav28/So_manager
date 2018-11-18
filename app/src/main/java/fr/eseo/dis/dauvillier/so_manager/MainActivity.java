package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private int role;
    private String password;
    private int idUser;
    private  static final String MY_PREFS_NAME="sessionUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnJury = (Button) findViewById(R.id.jury_button);
        btnProjets = (Button) findViewById(R.id.projets_button);
        btnProjets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnProjets(v);
            }
        });
        if(role==1) {

            btnJury.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickBtnJury(v);
                }
            });
        }else {
            btnJury.setVisibility(View.INVISIBLE);

        }
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
        SharedPreferences editor = this.getSharedPreferences(MY_PREFS_NAME,0);
        userName=editor.getString("userName",null);
        forename=editor.getString("forename",null);
        surname=editor.getString("surname",null);;
        role=editor.getInt("role",1000);;
        token= editor.getString("token",null);
        password=editor.getString("password",null);
        idUser=editor.getInt("idUSer",1000);

 //       Intent intent = getIntent();
//        userName=intent.getStringExtra("userName");
//        forename=intent.getStringExtra("forename");
//        surname=intent.getStringExtra("surname");
//        role=intent.getIntExtra("role",0);
//        token= intent.getStringExtra("token");
//        password=intent.getStringExtra("password");
//        idUser=intent.getIntExtra("idUser",1000);

    }
    public void changeActivity(Intent intent){
       /* intent.putExtra("userName",userName);
        intent.putExtra("token",token);
        intent.putExtra("forename",forename);
        intent.putExtra("surname",surname);
        intent.putExtra("role",role);
        intent.putExtra("password",password);
        intent.putExtra("idUser",idUser);*/

        startActivity(intent);
    }
}
