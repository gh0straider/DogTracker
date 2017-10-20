package com.gh0straider.dogtracker.data;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Helper for loading a list of Dogs or a single dog.
 */
public class DogLoader extends CursorLoader {
    public static DogLoader newAllArticlesInstance(Context context) {
        return new DogLoader(context, DogsContract.Items.buildDirUri());
    }

    public static DogLoader newInstanceForItemId(Context context, long itemId) {
        return new DogLoader(context, DogsContract.Items.buildItemUri(itemId));
    }

    private DogLoader(Context context, Uri uri) {
        super(context, uri, Query.PROJECTION, null, null, DogsContract.Items.DEFAULT_SORT);
    }

    public interface Query {
        String[] PROJECTION = {
                DogsContract.Items._ID,
                DogsContract.Items.NAME,
                DogsContract.Items.BIRTH_DATE,
                DogsContract.Items.TAG_NUMBER,
                DogsContract.Items.RABIES_DATE,
                DogsContract.Items.PARVO_DATE,
                DogsContract.Items.DISTEMPER_DATE,
                DogsContract.Items.WORM_DATE,
                DogsContract.Items.CYCLE_DATE,
                DogsContract.Items.PHOTO_URL,
        };

        int _ID = 0;
        int NAME = 1;
        int BIRTH_DATE = 2;
        int TAG_NUMBER = 3;
        int RABIES_DATE = 4;
        int PARVO_DATE = 5;
        int DISTEMPER_DATE = 6;
        int WORM_DATE = 5;
        int CYCLE_DATE = 6;
        int PHOTO_URL = 7;
    }
}
