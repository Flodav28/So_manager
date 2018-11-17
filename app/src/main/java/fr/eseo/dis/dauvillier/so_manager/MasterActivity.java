package fr.eseo.dis.dauvillier.so_manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.data.Jury;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;

public class MasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
    }

    public void getResponse(List response){
    }

    public void getResponse1(List response){
    }
    public void getResponse2(List response){
    }
    public void getResponse3(List response){
    }
    public void getMyProjet(String result,List<Projets> lProjet){
    }
    public void getMyJury(String result,List<Jury> lJury){
    }
    public void getMyDatesJury(List<Date> lDatesJury){
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void clickProjetCard(Projets projet) {

    }

}
