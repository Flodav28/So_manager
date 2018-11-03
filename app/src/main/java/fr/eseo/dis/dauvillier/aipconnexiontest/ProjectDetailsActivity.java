package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProjectDetailsActivity extends MasterActivity {

    private Project projet;
    private String userName;
    private String token;

    private TextView titre;
    private TextView description;
    private TextView note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        List<String> values=new ArrayList<String>();
        values.add("NOTES");
        values.add(userName);
        values.add(token);
        FetchDataLogon fetchDataNOTES = new FetchDataLogon(this, "NOTES", values);
        fetchDataNOTES.execute();
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        projet = (Project) data.getParcelable(ProjectsActivity.PROJECT_EXTRA);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        //note = findViewById(R.id.notation);

        titre.setText(projet.getTitle());
        description.setText(projet.getDescrip());
        //note.setText(());
    }

    private void loadFilmDetails() {
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
