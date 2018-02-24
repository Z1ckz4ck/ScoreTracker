package scoretracker.robert.scheffel.eu.scoretraker.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import scoretracker.robert.scheffel.eu.scoretraker.R;
import scoretracker.robert.scheffel.eu.scoretraker.entity.User;
import scoretracker.robert.scheffel.eu.scoretraker.services.ScoreTrackerService;
import scoretracker.robert.scheffel.eu.scoretraker.utils.StringUtils;


public class UserNew extends AppCompatActivity {
    private static final String TAG = "UserNew";
    private User user;
    private ScoreTrackerService sts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_new);
        sts = (ScoreTrackerService) getApplication();
        init();

    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        final EditText fname = (EditText) findViewById(R.id.txt_new_user_forname);
        final EditText ltname = (EditText) findViewById(R.id.txt_new_user_lastname);
        final EditText email = (EditText) findViewById(R.id.txt_new_user_email);
        Button btnSaveNewUser = (Button) findViewById(R.id.btn_saveNewUser);
        Button btnDeleteUser = (Button) findViewById(R.id.btn_saveDeleteUser);

        if (user == null) {
            user = new User();
            btnDeleteUser.setVisibility(View.GONE);
        } else {
            user = sts.getUser(user.getUserId());
            fname.setText(user.getFirstName());
            ltname.setText(user.getLastName());
            email.setText(user.geteMail());
            btnDeleteUser.setVisibility(View.VISIBLE);
        }


        btnSaveNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();

                if (StringUtils.isValidEmail(mail)) {
                    user.setFirstName(fname.getText().toString());
                    user.setLastName(ltname.getText().toString());
                    user.seteMail(mail);
                    sts.saveOrUpdate(user);
                    setResult(20);
                    finish();
                } else {

                    Log.i(TAG, "KEINE VALIDE MAIL ADRESSE");
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserNew.this);

                    final View mAlert = getLayoutInflater().inflate(R.layout.warning, null);
                    mBuilder.setView(mAlert);
                    final AlertDialog dialog = mBuilder.create();
                    Button btn_close = (Button) mAlert.findViewById(R.id.btn_cl_mail_warning);
                    btn_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }

        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    Log.i(TAG, "user geloescht " + user.getUserId());
                    sts.deleteUser(user.getUserId());
                    setResult(20);
                    finish();
                }
            }
        });
    }

}
