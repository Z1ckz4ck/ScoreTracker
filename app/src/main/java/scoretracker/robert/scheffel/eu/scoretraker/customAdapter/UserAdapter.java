package scoretracker.robert.scheffel.eu.scoretraker.customAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import scoretracker.robert.scheffel.eu.scoretraker.activity.LvUser;
import scoretracker.robert.scheffel.eu.scoretraker.activity.UserNew;
import scoretracker.robert.scheffel.eu.scoretraker.entity.User;


/**
 * Created by z1ckz4ck on 08.04.17.
 */
public class UserAdapter extends ArrayAdapter<LvUser> {
    private static final String TAG = "UserAdapter";

    private Context context;
    private List<LvUser> users;
    private ListView lvUsers;

    public UserAdapter(Context context, int resource, List<LvUser> users) {
        super(context, resource, users);
        this.context = context;
        this.users = users;
        this.lvUsers = lvUsers;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View v = convertView;


        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.lv_item_user, null);
        }

        final LvUser lvUser = getItem(position);

        if (lvUser != null) {
            TextView tfFirstname = (TextView) v.findViewById(R.id.lv_item_user_firstname);
            TextView tfLastname = (TextView) v.findViewById(R.id.lv_item_user_lastname);
            ImageButton btnConfig = (ImageButton) v.findViewById(R.id.btn_configUser);

            if(lvUser.isSelected()){
                v.setBackgroundColor(Color.BLACK);
            }

            View.OnClickListener li =  new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ListView) parent).performItemClick(v, position, 0);
                    v.setBackgroundColor(Color.BLACK);
                    notifyDataSetChanged();
                }
            };

            if (tfFirstname != null) {
                tfFirstname.setText(lvUser.getFirstName());
                tfFirstname.setOnClickListener(li);
            }
            if (tfLastname != null) {
                tfLastname.setText(lvUser.getLastName());
                tfLastname.setOnClickListener(li);
            }

            if (btnConfig != null) {
                // btnConfig = lvUser.getConfig();
                btnConfig.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Log.i(TAG, "Button cliked");
                        Intent userNew = new Intent(getContext(), UserNew.class);
                        User configUser = new User();
                        configUser.setUserId(lvUser.getUserId());
                        userNew.putExtra("user", configUser);
                      //  getContext().startActivity(userNew);
                       ((Activity) context).startActivityForResult(userNew, 20);
                    }
                });
            }

        }

        return v;
    }


}
