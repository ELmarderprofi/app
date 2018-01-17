package com.quicknote.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Daniel on 16.12.17.
 */
public class Preferences {

    private static final String PREFERENCE_NOTE_TITLE = "note_title";
    private static final String PREFERENCE_NOTE_MESSAGE = "note_message";

    public static String getNoteTitle(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
                getString(PREFERENCE_NOTE_TITLE, null);
    }

    public static void setNoteTitle(Context context, String string) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(context).edit();
        editor.putString(PREFERENCE_NOTE_TITLE, string);
        editor.apply();
    }

    public static String getNoteMessage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).
                getString(PREFERENCE_NOTE_MESSAGE, null);
    }

    public static void setNoteMessage(Context context, String string) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(context).edit();
        editor.putString(PREFERENCE_NOTE_MESSAGE, string);
        editor.apply();
    }
}
