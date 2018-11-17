package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.data.Eleves;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ElevesDao;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Projets;

public class ProjectDetailsActivity extends AppCompatActivity {
    public static final String PROJECT_EXTRA = "project_extra";

    private Projets projet;
    private String userName;
    private String token;

    private TextView titre;
    private TextView description;
    private TextView note;
    private ListView membre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        projet = (Projets) intent.getSerializableExtra(PROJECT_EXTRA);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        membre = findViewById(R.id.listViewStudents);

        List<Eleves>etudiantList=getEtudiantList(projet.getIdProject());
        String membre=" " +getEtudiantNom(etudiantList,this.membre);
        titre.setText(projet.getTitle());
        description.setText(projet.getDescrip());

        //note.setText(());
        /*List<String> values=new ArrayList<String>();
        values.add("NOTES");
        values.add(userName);
        values.add(token);
        FetchDataLogon fetchDataNOTES = new FetchDataLogon(this, "NOTES", values);
        fetchDataNOTES.execute();*/
    }
    public String getEtudiantNom(List<Eleves> elevesList,ListView listView){
        String nom=null;
        TextView textView;
        for(Eleves eleves :elevesList){
            textView.setText(eleves.getForename());
            listView.addFooterView(textView);
        }
        return nom;
    }
    public List<Eleves>  getEtudiantList(int id){
        ElevesDao elevesDao = ProjectsDatabase.getDatabase(this).elevesDao();
        return elevesDao.getElevesDuProjet(id);
    }

    /*
    Pays pays = null;
    Artiste reali = null;
    int minNote = 0;
    int maxNote = 0;
    float avgNote = 0;
    int nbNotes = 0;

    Log.d("FilmDetailsActivity", "Find Country Information for film: " + film.getTitre());

    int indice = 0;
    while (indice < DummyData.getPays().size() && pays == null) {
      if (DummyData.getPays().get(indice).getCode().equals(film.getCodePays())) {
        pays = DummyData.getPays().get(indice);
      } else {
        indice++;
      }
    }

    Log.d("FilmDetailsActivity", "Find Director Information for film: " + film.getTitre());

    indice = 0;
    while (indice < DummyData.getArtistes().size() && reali == null) {
      if (DummyData.getArtistes().get(indice).getIdArtiste() == (film.getIdRealisateur())) {
        reali = DummyData.getArtistes().get(indice);
      } else {
        indice++;
      }
    }

    Log.d("FilmDetailsActivity", "Find Roles Information for film: " + film.getTitre());

    List<RoleWithActor> actorRole = new ArrayList<>();
    for (Role role : DummyData.getRoles()) {
      if (role.getIdFilm() == film.getIdFilm()) {
        Artiste artiste = null;
        indice = 0;

        Log.d("FilmDetailsActivity",
            "Find Actor Information for role: " + role.getNomRole() + " in film: " + film
                .getTitre());

        while (indice < DummyData.getArtistes().size() && artiste == null) {
          if (DummyData.getArtistes().get(indice).getIdArtiste() == role.getIdActeur()) {
            artiste = DummyData.getArtistes().get(indice);
            actorRole.add(new RoleWithActor(artiste, role));

          } else {
            indice++;
          }
        }
      }
    }
    aa.setRoles(actorRole);

    Log.d("FilmDetailsActivity", "Find Notation Information for film: " + film.getTitre());

    List<Notation> notations = new ArrayList<>();
    for (Notation notation : DummyData.getNotations()) {
      if (notation.getId_film() == film.getIdFilm()) {
        notations.add(notation);
      }
    }
    if (notations == null || notations.size() == 0) {
      nbNotes = 0;
      avgNote = 0;

    } else {
      avgNote = minNote;
      nbNotes = notations.size();
      for (Notation notation : notations) {
        avgNote = avgNote + notation.getNote();
      }
      avgNote = (float) avgNote / (float) nbNotes;
    }

    Log.d("FilmDetailsActivity", "Populating View for film: " + film.getTitre());

    annee.setText(String.valueOf(film.getAnnee()) + " (" + pays.getNom() + ")");
    realisateur.setText(getString(R.string.realisateur_label) + reali.getPrenom() + " " + reali.getNom());
    DecimalFormat df = new DecimalFormat("0.0");
    note.setText(df.format(avgNote) + getString(R.string.average_note_from) + nbNotes + getString(R.string.note_nbr_users));
    aa.notifyDataSetChanged();
    if (avgNote == 0) {
      btnDetails.setVisibility(View.GONE);
    } else {
      btnDetails.setVisibility(View.VISIBLE);
    }*/
        /*float avgNote;
        int nbNotes;

        Pays pays = FilmographyDatabase.getDatabase(this).paysDao().findPaysFromCode(film.getCodePays());
        Artiste reali = FilmographyDatabase.getDatabase(this).artisteDao().findArtisteFromId(film.getIdRealisateur());
        List<RoleAvecArtiste> roles = FilmographyDatabase.getDatabase(this).roleDao().findRolesWithActeursForFilm(film.getIdFilm());
        List<Notation> notations = (List<Notation>)FilmographyDatabase.getDatabase(this).notationDao()
                .findNotationsForFilm(film.getIdFilm());
        if(notations == null || notations.size()==0){
            avgNote = 0;
            nbNotes = 0;
        }
        else{
            avgNote = 0;
            nbNotes = notations.size();
            for(Notation notation : notations){
                avgNote += notation.getNote();
            }
            avgNote = (float)avgNote/(float)nbNotes;
        }
        annee.setText(String.valueOf(film.getAnnee()) + " (" + pays.getNom() + ")");
        realisateur.setText(getString(R.string.realisateur_label) + reali.getPrenom() + " " + reali.getNom());
        DecimalFormat df = new DecimalFormat("0.0");
        note.setText(df.format(avgNote) + getString(R.string.average_note_from) + nbNotes + getString(R.string.note_nbr_users));
        filmRoleAdapter.setRoles(roles);
        filmRoleAdapter.notifyDataSetChanged();
        if (avgNote == 0) {
            btnDetails.setVisibility(View.GONE);
        } else {
            btnDetails.setVisibility(View.VISIBLE);
        }
    }*/
}