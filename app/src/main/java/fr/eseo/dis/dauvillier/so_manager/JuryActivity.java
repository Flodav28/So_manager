package fr.eseo.dis.dauvillier.so_manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.adapters.JuryAdapter;
import fr.eseo.dis.dauvillier.so_manager.data.Jury;

public class JuryActivity extends MasterActivity {

    public static int NEW_CARD_COUNTER;
    private  static final String MY_PREFS_NAME="sessionUser";

    private JuryAdapter juryAdapter;
    private String forename;
    private String surname;
    private String userName;
    private String token;
    private int role;
    private String password;
    private int idUser;
    List<String> values1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury);
        init();
        getJuryList();
    }
    @Override
    public void  getResponse(List<Object> response){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("token",(String)response.get(1));
        editor.apply();
       getJuryList();
    }
    public void  getJuryList(){
        List<String> values1=new ArrayList<>();
        values1.add("MYJUR");
        values1.add(userName);
        values1.add(token);
        FetchDataLogon fetchDataMYJUR = new FetchDataLogon(this, "MYJUR", values1);
        fetchDataMYJUR.execute();
    }



    public void getMyJury(String result,List<Jury> lJury){
        if("OK".equals(result)) {
            NEW_CARD_COUNTER = 0;
            RecyclerView recycler = (RecyclerView) findViewById(R.id.juryList);
            recycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(llm);
            juryAdapter = new JuryAdapter(this);
            recycler.setAdapter(juryAdapter);
            loadAllJuryData(lJury);
        }else{
            values1 = new ArrayList<>();
            values1.add("LOGON");
            values1.add(userName);
            values1.add(password);
            FetchDataLogon fetchDataLIJUR = new FetchDataLogon(this, "LOGON", values1);
            fetchDataLIJUR.execute();
        }
    }

    private void loadAllJuryData(List<Jury> lJury){
        juryAdapter.setJury(lJury);
        juryAdapter.notifyDataSetChanged();
    }

    public void clickJuryCard(Jury jury) {
        Intent intent = new Intent(JuryActivity.this, JuryDetailsActivity.class);
        intent.putExtra("idJury", jury.getIdJury());
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
}
