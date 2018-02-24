package scoretracker.robert.scheffel.eu.scoretraker.customAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.R;
import scoretracker.robert.scheffel.eu.scoretraker.entity.Parcour;
import scoretracker.robert.scheffel.eu.scoretraker.services.ScoreTrackerService;


public class ParcourAdapter extends ArrayAdapter<Parcour> {

    private ScoreTrackerService sts;
    private List<Parcour> parcours;

    public ParcourAdapter(Context context, int resource, List<Parcour> parcours, ScoreTrackerService sts) {
        super(context, resource, parcours);
        this.parcours = parcours;
        this.sts = sts;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.lv_item_parcour, null);
        }

        final Parcour lvParcour = getItem(position);

        if(lvParcour != null){
            TextView tfCounttargets = (TextView)v.findViewById(R.id.lv_count_targets);
            TextView tfParcouNr = (TextView)v.findViewById(R.id.lv_pacour_nr);
            TextView tfParcourName = (TextView)v.findViewById(R.id.lv_pacour_name);
            TextView tfParcourNameHint = (TextView) v.findViewById(R.id.lv_pacour_name_txt);
            TextView tfParcourTargetHint= (TextView) v.findViewById(R.id.count_targets_txt);
            TextView tfParcourNrhint = (TextView) v.findViewById(R.id.pacour_nr_txt);

            ImageButton btn = (ImageButton) v.findViewById(R.id.lv_btn_delete_Parcour);

            View.OnClickListener li =  new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);
                }
            };

            if (tfCounttargets != null){
                tfCounttargets.setText(String.valueOf(lvParcour.getTargetCount()));
                tfCounttargets.setOnClickListener(li);
            }

            if(tfParcouNr != null){
                tfParcouNr.setText(String.valueOf(position + 1));
                tfParcouNr.setOnClickListener(li);
            }

            if(tfParcourName != null){
                tfParcourName.setText(lvParcour.getName());
                tfParcourName.setOnClickListener(li);
            }
            if(tfParcourTargetHint != null) {
                tfParcourTargetHint.setOnClickListener(li);
            }
            if(tfParcourNameHint != null){
                tfParcourNameHint.setOnClickListener(li);
            }
            if(tfParcourNrhint != null){
                tfParcourNrhint.setOnClickListener(li);
            }

         btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("Adapter","Button Delte Parcour clicked");
                    Parcour parcour =getItem(position);
                    parcours.remove(parcour);
                    sts.deleteParcour(parcour);
                    notifyDataSetChanged();

                }
            });

        }

        return v;
    }


}
