package fr.eseo.dis.dauvillier.aipconnexiontest.adapters;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eseo.dis.dauvillier.aipconnexiontest.JuryActivity;
import fr.eseo.dis.dauvillier.aipconnexiontest.R;
import fr.eseo.dis.dauvillier.aipconnexiontest.data.Jury;

public class JuryAdapter extends RecyclerView.Adapter<JuryAdapter.JuryViewHolder> {

    private JuryActivity activity;
    private List<Jury> jury;
    private List<Integer> positionsExpanded;

    public JuryAdapter(JuryActivity juryActivity) {
        positionsExpanded = new ArrayList<>();
        this.activity = juryActivity;
        setJury(new ArrayList<Jury>());
    }

    public void setJury(List<Jury> jury) {
        this.jury = jury;
        System.out.println("jury : " + this.jury);
    }

    @Override
    public int getItemCount() {
        return jury.size();
    }

    @Override
    public JuryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View juryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jury_card_layout, parent, false);
 //       CardView juryCardView = (CardView) juryView;
 //       juryCardView.setCardElevation(3 * JuryActivity.NEW_CARD_COUNTER++);
        return new JuryAdapter.JuryViewHolder(juryView);
    }

    @Override
    public void onBindViewHolder(@NonNull JuryAdapter.JuryViewHolder holder, final int position) {
        final Jury jury = this.jury.get(position);
        holder.jury.setText(String.valueOf(jury.getIdJury()));

        holder.view.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.clickJuryCard(jury);
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
                TextView resume = (TextView) v.findViewById(R.id.tv_film_resume);
                TextView resumeLabel = (TextView) v.findViewById(R.id.tv_film_resume_label);
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

    class JuryViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final TextView jury;

        public JuryViewHolder(View view) {
            super(view);
            this.view = view;
            jury = view.findViewById(R.id.jury);
        }
    }
}
