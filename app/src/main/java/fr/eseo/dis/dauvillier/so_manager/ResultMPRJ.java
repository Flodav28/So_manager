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

public class ResultMPRJ {
    private static final String  API="MPRJ";
    List<Projets>  lMyProjet;

    public String data;
    public MasterActivity activity;
    public JSONObject jsonProjetMain;
    public TraitementProjetDB traitementProjetDB;
    public ResultMPRJ(String data,MasterActivity activity) throws JSONException {
        this.data=data;
        this.activity=activity;
        this.jsonProjetMain= TraitementProjetDB.getJsonObject(data);
        this.traitementProjetDB=new TraitementProjetDB(activity,this.jsonProjetMain);
          lMyProjet= new ArrayList<>();
        getResultRequest();
    }



    public void getResultRequest()   {
        String responseValues=null;
        TraitementUtilisateurDB traitementUtilisateurDB;
        TraitementEleveDB traitementEleveDB;
        try {
            if(this.traitementProjetDB.resultOk()){
                responseValues=(String)this.jsonProjetMain.get("result");
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
                    lMyProjet.add(traitementProjetDB.getProjet());
                    JSONArray listeEtudiant=null;
                    JSONObject jsonEtudiant=null;
                    listeEtudiant = jsonProjet.getJSONArray("students");
                    for(int j = 0; j < listeEtudiant.length(); j++) {
                        jsonEtudiant = listeEtudiant.getJSONObject(j);
                        traitementEleveDB = new TraitementEleveDB(activity,jsonEtudiant);
                        traitementEleveDB.traitement(traitementProjetDB.getProjet().getIdProject());
                    }
                }
            }else{
                responseValues=(String)this.jsonProjetMain.get("result");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.getMyProjet(responseValues,lMyProjet);
    }


}
