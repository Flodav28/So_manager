package fr.eseo.dis.dauvillier.so_manager;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementUtilisateurDB;

public class ResultLOGON {

    public MasterActivity activity;
    TraitementUtilisateurDB traitementUtilisateur;
    JSONObject jsonUtilisateur;
    private static final String  API="LOGON";
    private  static final String MY_PREFS_NAME="sessionUser";


    public ResultLOGON(String data,MasterActivity activity)   {
        this.activity=activity;
        this.jsonUtilisateur= TraitementUtilisateurDB.getJsonObject(data);
        this.traitementUtilisateur=new TraitementUtilisateurDB(activity,this.jsonUtilisateur);
        try {
            getResultRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getResultRequest( ) throws JSONException {

        List <Object> responseValues=new ArrayList<>();
            //result est valide
            if(this.traitementUtilisateur.resultOk()){
                //this.traitementUtilisateur.traitement(API);
                responseValues.add((String)this.jsonUtilisateur.get("result"));
                responseValues.add((String)this.jsonUtilisateur.get("token"));
           }else{
                responseValues.add((String)this.jsonUtilisateur.get("result"));
            }
        activity.getResponse(responseValues);
    }
}
