package fr.eseo.dis.dauvillier.so_manager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementUtilisateurDB;

public class ResultMYINF {
    public MasterActivity activity;
    TraitementUtilisateurDB traitementUtilisateur;
    JSONObject jsonUtilisateur;
    private static final String  API="LOGON";


    public ResultMYINF(String data,MasterActivity activity)   {
        this.activity=activity;
        this.jsonUtilisateur= TraitementUtilisateurDB.getJsonObject(data);
        this.traitementUtilisateur=new TraitementUtilisateurDB(activity,this.jsonUtilisateur);

            getResultRequest();

    }
    public void getResultRequest() {

        List<String> responseValues = new ArrayList<>();


        //result est valide

        try {
            if (this.traitementUtilisateur.resultOk()) {
                JSONArray listeJsonProject = null;
                listeJsonProject = jsonUtilisateur.getJSONArray("info");
                JSONObject role = (JSONObject) listeJsonProject.get(0);
                this.traitementUtilisateur = new TraitementUtilisateurDB(activity, role);
                this.traitementUtilisateur.traitement(API);
                responseValues.add((String)role.get("username"));
                responseValues.add((String) role.get("surname"));
                responseValues.add((String) role.get("forename"));
                responseValues.add((String) role.getString("idRole"));
                responseValues.add(String.valueOf(this.traitementUtilisateur.getUtilisateur().getIdUser()));

            } else {
                responseValues.add((String) this.jsonUtilisateur.get("result"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.getResponse1(responseValues);

    }

}
