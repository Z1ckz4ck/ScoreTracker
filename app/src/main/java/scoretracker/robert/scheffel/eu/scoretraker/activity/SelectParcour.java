package scoretracker.robert.scheffel.eu.scoretraker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.R;
import scoretracker.robert.scheffel.eu.scoretraker.customAdapter.ParcourAdapter;
import scoretracker.robert.scheffel.eu.scoretraker.entity.Parcour;
import scoretracker.robert.scheffel.eu.scoretraker.services.ScoreTrackerService;


public class SelectParcour extends AppCompatActivity {

    private static final String TAG = "SelectPacour";

    private List<Parcour> pacours;
    private ScoreTrackerService sts;
    private ListView parcourList;
    private ParcourAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_parcour);
        sts = (ScoreTrackerService) getApplication();

        // TextView title = (TextView) findViewById(R.id.txt_pacourlist);
        Button btn_createNew = (Button) findViewById(R.id.btn_create_new_parcour);
        btn_createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPacour = new Intent(SelectParcour.this, ParcourNew.class);

                startActivityForResult(newPacour, 30);
            }
        });
        parcourList = (ListView) findViewById(R.id.lv_parcours);
        parcourList.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        adapter = new ParcourAdapter(this, R.layout.lv_item_parcour,sts.getPacours(),sts);
        parcourList.setAdapter(adapter);
        parcourList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long viewId = view.getId();

                Log.i(TAG, "Cklick on Item Parcour. Position " + position);
                Parcour itemAtPosition = (Parcour) parent.getItemAtPosition(position);
                sts.setActiveParcour(itemAtPosition);
                sts.updateLastParcour(itemAtPosition);
                parcourList.setSelection(position);
                setResult(40);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 30 && resultCode == 30) {
            Log.i(TAG, "Parcour was added");
            adapter = new ParcourAdapter(this, R.layout.lv_item_parcour,sts.getPacours(),sts);
            parcourList.setAdapter(adapter);
            ((ParcourAdapter) parcourList.getAdapter()).notifyDataSetChanged();
        }
    }
}
