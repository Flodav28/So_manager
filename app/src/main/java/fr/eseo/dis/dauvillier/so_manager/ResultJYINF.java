package fr.eseo.dis.dauvillier.so_manager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementEleveDB;
import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementProjetDB;
import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementUtilisateurDB;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;

public class ResultJYINF {
    private static final String  API="JYINF";

    public String data;
    public MasterActivity activity;
    public JSONObject jsonProjetMain;
    public TraitementProjetDB traitementProjetDB;
    public ResultJYINF(String data,MasterActivity activity) throws JSONException {
        this.data=data;
        this.activity=activity;
        this.jsonProjetMain= TraitementProjetDB.getJsonObject(data);
        this.traitementProjetDB=new TraitementProjetDB(activity,this.jsonProjetMain);
        getResultRequest();
    }


    public JSONObject getJson(String data) throws JSONException {

        return  new JSONObject(data);
    }
    public void getResultRequest()   {
        List<String> responseValues=new ArrayList<>();
        TraitementUtilisateurDB traitementUtilisateurDB;
        TraitementEleveDB traitementEleveDB;
        List<Projets> lprojet ;
        try {
            if(this.traitementProjetDB.resultOk()){
                JSONArray listeJsonProject = jsonProjetMain.getJSONArray("projects");
                JSONObject jsonProjet=null;
                JSONObject jsonUtilisateur=null;

                for(int i = 0; i < listeJsonProject.length()-1; i++) {
                    jsonProjet= listeJsonProject.getJSONObject(i);

                    jsonUtilisateur = jsonProjet.getJSONObject("supervisor");
                    traitementUtilisateurDB= new TraitementUtilisateurDB(activity,jsonUtilisateur);
                    traitementUtilisateurDB.traitement(API);
                    traitementProjetDB = new TraitementProjetDB(activity,jsonProjet);
                    traitementProjetDB.traitement(API,traitementUtilisateurDB.getUtilisateur().getIdUser(),null);
                    JSONArray listeEtudiant=null;
                    JSONObject jsonEtudiant=null;
                    listeEtudiant = jsonProjet.getJSONArray("students");

                    for(int j = 0; j < listeEtudiant.length(); j++) {

                        jsonEtudiant = listeEtudiant.getJSONObject(j);
                        traitementEleveDB = new TraitementEleveDB(activity,jsonEtudiant);
                        traitementEleveDB.traitement(traitementProjetDB.getProjet().getIdProject());

                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("flodav2", String.valueOf(traitementProjetDB.getlProjet().size()));
        activity.getResponse1(responseValues);
    }
}
