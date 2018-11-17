package fr.eseo.dis.dauvillier.so_manager.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.ProjectsActivity;
import fr.eseo.dis.dauvillier.so_manager.R;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;

public class ProjetsAdapter extends RecyclerView.Adapter<ProjetsAdapter.ProjetsViewHolder> {

    private ProjectsActivity activity;
    private List<Projets> projets;
    private List<Integer> positionsExpanded;

    public ProjetsAdapter(ProjectsActivity projectsActivity) {
        positionsExpanded = new ArrayList<>();
        this.activity = projectsActivity;
        setProjets(new ArrayList<Projets>());
    }

    public void setProjets(List<Projets> projets) {
        this.projets = projets;
    }

    @Override
    public int getItemCount() {
        return projets.size();
    }

    @Override
    public ProjetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card_layout, parent, false);
        CardView projetCardView = (CardView) projetView;
        projetCardView.setCardElevation(3 * ProjectsActivity.NEW_CARD_COUNTER++);
        return new ProjetsAdapter.ProjetsViewHolder(projetView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjetsViewHolder holder, final int position) {
        final Projets projet = projets.get(position);
        //TODO
        holder.titreProjet.setText(projet.getTitle());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickProjetCard(projet);
            }
        });

        /*if (positionsExpanded.contains(position)) {
            holder.filmResume.setVisibility(View.VISIBLE);
        } else {
            holder.filmResume.setVisibility(View.GONE);
        }*/

        /*holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView resume = (TextView) v.findViewById(R.id.tv_projet_resume);
                TextView resumeLabel = (TextView) v.findViewById(R.id.tv_projet_resume_label);
                if (positionsExpanded.contains(position)) {
                    resume.setVisibility(View.GONE);
                    resumeLabel.setVisibility(View.GONE);
                    positionsExpanded.remove(new Integer(position));
                } else {
                    resume.setVisibility(View.VISIBLE);
                    resumeLabel.setVisibility(View.VISIBLE);
                    positionsExpanded.add(position);
                }
                return true;
            }
        });*/
    }

    class ProjetsViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView titreProjet;

        public ProjetsViewHolder(View view) {
            super(view);
            this.view = view;
            titreProjet = view.findViewById(R.id.titre);
        }
    }
}