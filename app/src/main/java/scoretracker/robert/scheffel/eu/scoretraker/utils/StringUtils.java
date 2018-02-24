package scoretracker.robert.scheffel.eu.scoretraker.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by z1ckz4ck on 14.04.17.
 */
public class StringUtils {

    private StringUtils(){}

    public static boolean isValidEmail(CharSequence in){
        return !TextUtils.isEmpty(in) && Patterns.EMAIL_ADDRESS.matcher(in).matches();
    }
}
