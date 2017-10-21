package com.gh0straider.dogtracker.data;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/**
 * Helper for loading a list of Dogs or a single dog.
 */
public class DogLoader extends CursorLoader {
    public static DogLoader newAllArticlesInstance(Context context) {
        return new DogLoader(context, DogsContract.Dogs.buildDirUri());
    }

    public static DogLoader newInstanceForItemId(Context context, long itemId) {
        return new DogLoader(context, DogsContract.Dogs.buildItemUri(itemId));
    }

    private DogLoader(Context context, Uri uri) {
        super(context, uri, Query.PROJECTION, null, null, DogsContract.Dogs.DEFAULT_SORT);
    }

    public interface Query {
        String[] PROJECTION = {
                DogsContract.Dogs._ID,
                DogsContract.Dogs.NAME,
                DogsContract.Dogs.BIRTH_DATE,
                DogsContract.Dogs.TAG_NUMBER,
                DogsContract.Dogs.RABIES_DATE,
                DogsContract.Dogs.PARVO_DATE,
                DogsContract.Dogs.DISTEMPER_DATE,
                DogsContract.Dogs.WORM_DATE,
                DogsContract.Dogs.CYCLE_DATE,
                DogsContract.Dogs.PHOTO_URL,
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
