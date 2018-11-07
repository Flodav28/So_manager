package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementEleveDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementJuryDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementProjetDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.Traitement.TraitementUtilisateurDB;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;

public class ResultMYJUR {
    private static final String  API="MYJUR";

    public String data;
    public MasterActivity activity;
    public JSONObject jsonJury;
    public TraitementJuryDB traitementJuryDB;
    public List<Jury> lMyJury;
    public List<Date> lDatesMyJury;
    String responseValues;
    public ResultMYJUR(String data,MasterActivity activity) throws JSONException {
        this.data=data;
        this.lMyJury=new ArrayList<>();
        this.lDatesMyJury=new ArrayList<>();
        this.activity=activity;
        this.jsonJury= TraitementProjetDB.getJsonObject(data);
        this.traitementJuryDB=new TraitementJuryDB(activity,this.jsonJury);
        getResultRequest();
    }



    public void getResultRequest()   {
         responseValues=null;
        TraitementUtilisateurDB traitementUtilisateurDB;
        TraitementProjetDB traitementProjetDB;

        try {
            if(this.traitementJuryDB.resultOk()){
                responseValues=(String)this.jsonJury.get("result");
                JSONArray listeJury =jsonJury.getJSONArray("juries");
                JSONObject jsonJurys=null;

                for(int l=0;l<listeJury.length();l++){

                    jsonJurys=listeJury.getJSONObject(l);
                    traitementJuryDB= new TraitementJuryDB(activity,jsonJurys);
                    traitementJuryDB.traitement();
                    lMyJury.add(traitementJuryDB.getJury());
                    JSONArray listeJsonProject = jsonJurys.getJSONObject("info").getJSONArray("projects");
                    JSONObject jsonProjet=null;
                    JSONObject jsonUtilisateur=null;

                    for(int i = 0; i < listeJsonProject.length()-1; i++) {

                        jsonProjet= listeJsonProject.getJSONObject(i);
                        jsonUtilisateur = jsonProjet.getJSONObject("supervisor");
                        traitementUtilisateurDB= new TraitementUtilisateurDB(activity,jsonUtilisateur);
                        traitementUtilisateurDB.traitement(API);
                        traitementProjetDB = new TraitementProjetDB(activity,jsonProjet);
                        traitementProjetDB.traitementLIJUR(API,traitementUtilisateurDB.getUtilisateur().getIdUser(),traitementJuryDB.getJury().getIdJury());

                    }
                }
            }else{
                responseValues=(String)this.jsonJury.get("result");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.getMyJury(responseValues,lMyJury);
    }

}
