package com.odn.kotlinretrofi;

import android.content.Intent;
import android.text.format.DateUtils;

/**
 * Created by EDGAR ARANA on 03/07/2017.
 */

public class ConvertTime {

    public static CharSequence converteTimestamp(String mileSegundos) {
        Long time = Long.parseLong(mileSegundos) * 1000;
        String txt = (String) DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        return Character.toUpperCase(txt.charAt(0)) + txt.substring(1);

    }
}
