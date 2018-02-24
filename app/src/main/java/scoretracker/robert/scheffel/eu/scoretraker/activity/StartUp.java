package scoretracker.robert.scheffel.eu.scoretraker.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import scoretracker.robert.scheffel.eu.scoretraker.R;
import scoretracker.robert.scheffel.eu.scoretraker.customAdapter.UserAdapter;
import scoretracker.robert.scheffel.eu.scoretraker.entity.User;
import scoretracker.robert.scheffel.eu.scoretraker.enums.ScoreType;
import scoretracker.robert.scheffel.eu.scoretraker.services.ScoreTrackerService;

public class StartUp extends AppCompatActivity {

    private static final String TAG = "ScoreTrackerActivity";

    private Button btnStart;
    private ScoreTrackerService sts;
    private Spinner spScorePoints;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        sts = (ScoreTrackerService) getApplication();
        init();
        start();
    }

    /**
     * Initialize the Activity
     */
    public void init() {
        //Pacour
        if (sts.getLastParcour() != null) {
            final TextView selectedPacour = (TextView) findViewById(R.id.txt_selected_pakour);
            selectedPacour.setText(sts.getLastParcour().getName());
        }
        Button btnSelectParcour = (Button) findViewById(R.id.btn_select_parcour);
        btnSelectParcour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent selectPar = new Intent(StartUp.this, SelectParcour.class);
                startActivityForResult(selectPar, 40);

            }
        });
        //ScorePoints
        spScorePoints = (Spinner) findViewById(R.id.sp_score);
        addScoresToSpinner(spScorePoints);
        spScorePoints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ScoreType itemAtPosition = (ScoreType) parent.getItemAtPosition(position);
                if(sts.getActiveParcour() != null) {
                    sts.getActiveParcour().setScoreType(itemAtPosition);
                    Log.i(TAG, sts.getActiveParcour().getScoreType().getDiscription());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btn_addScoresNew = (Button) findViewById(R.id.btn_add_scores);
        btn_addScoresNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scoresNew = new Intent(StartUp.this, ScoresNew.class);
                startActivity(scoresNew);
            }
        });


        //Users -> open user Activity to add User
        Button btn_addUserNew = (Button) findViewById(R.id.btn_adduser);
        btn_addUserNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userNew = new Intent(StartUp.this, UserNew.class);
                startActivityForResult(userNew, 20);

            }
        });
        ListView lvUsers = (ListView) findViewById(R.id.lv_users);
        createListViewUser(lvUsers);
    }

    private void createListViewUser(final ListView lvUsers) {

        List<LvUser> lvUserItems = addUsersToLV();
        final int lv_item_user_id = R.layout.lv_item_user;
        adapter = new UserAdapter(this, lv_item_user_id, lvUserItems);

        lvUsers.setAdapter(adapter);
        lvUsers.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "User selected " + position);
                LvUser lvUser = (LvUser) parent.getItemAtPosition(position);
                if (sts.getActiveUserL().contains(sts.getUser(lvUser.getUserId()))) {
                    Log.i(TAG, "User removed from List " + position + " " + lvUser.getFirstName());
                    sts.getActiveUserL().remove(sts.getUser(lvUser.getUserId()));
                    view.setSelected(false);
                } else if (!sts.getActiveUserL().contains(sts.getUser(lvUser.getUserId()))) {
                    sts.getActiveUserL().add(sts.getUser(lvUser.getUserId()));
                    lvUsers.setSelection(position);
                    view.setSelected(true);
                    Log.i(TAG, "User add to List " + position + " " + lvUser.getFirstName());
                }

                lvUsers.setAdapter(adapter);
                ((UserAdapter) lvUsers.getAdapter()).notifyDataSetChanged();
            }
        });



  /*  lvUsers.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    });
*/


    }


    /**
     * adds all Users to Listview
     *
     * @return
     */
    private List<LvUser> addUsersToLV() {
        List<LvUser> result = new ArrayList<>();
        List<User> allUser = sts.refreshAndgetAllUser();
        for (final User u : allUser) {
            LvUser lvUser = new LvUser();
            lvUser.setUserId(u.getUserId());
            lvUser.setFirstName(u.getFirstName());
            lvUser.setLastName(u.getLastName());
            ImageButton imageButton = new ImageButton(getApplicationContext());
            lvUser.setConfig(imageButton);
            result.add(lvUser);
        }

        return result;
    }

    /**
     * @param spScorePoints
     */
    private void addScoresToSpinner(Spinner spScorePoints) {
        List<ScoreType> scoreTyps = new ArrayList<>();
        scoreTyps.add(ScoreType.NFAS_STANDARD);
        scoreTyps.add(ScoreType.DBSV_STANDARD);
        scoreTyps.add(ScoreType.HUNTER_3D);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, scoreTyps);
        spScorePoints.setAdapter(adapter);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == 20) {
            createListViewUser((ListView) findViewById(R.id.lv_users));
            adapter.notifyDataSetChanged();

        }
        if (requestCode == 40 && resultCode == 40) {
            TextView view = (TextView) findViewById(R.id.txt_selected_pakour);
            view.setText(sts.getActiveParcour().getName());
        }
    }


    /**
     * starts a new Pacour
     */
    private void start() {
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Start Button clicked");
                Log.i(TAG, "Anzahl USERS " + sts.getActiveUserL().size() + " Parcpur " + sts.getActiveParcour().getName() + " Scoretype " + sts.getActiveParcour().getScoreType());
            }
        });
        //TODO: update LastChosen Parcour
        Toast.makeText(StartUp.this, "Start Pacour", Toast.LENGTH_SHORT).show();
        //Intent selectPar = new Intent(StartUp.this, ActiveParcour.class);
        //startActivityForResult(selectPar, 50);
    }
}
