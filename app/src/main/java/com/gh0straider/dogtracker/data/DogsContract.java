package com.gh0straider.dogtracker.data;

import android.net.Uri;

public class DogsContract {
	public static final String CONTENT_AUTHORITY = "com.gh0straider.dogtracker";
	public static final Uri BASE_URI = Uri.parse("content://com.gh0straider.dogtracker");

	interface ItemsColumns {
		/** Type: INTEGER PRIMARY KEY AUTOINCREMENT */
		String _ID = "_id";
		/** Type: TEXT */
		String NAME = "name";
		/** Type: TEXT NOT NULL */
		String BIRTH_DATE = "dob_date";
		/** Type: TEXT NOT NULL */
		String TAG_NUMBER = "tag_number";
		/** Type: TEXT NOT NULL */
		String RABIES_DATE = "rabies_date";
		/** Type: TEXT NOT NULL */
		String PARVO_DATE = "parvo_date";
		/** Type: TEXT NOT NULL */
		String DISTEMPER_DATE = "distemper_date";
		/** Type: TEXT NOT NULL */
		String WORM_DATE = "worm_date";
		/** Type: TEXT NOT NULL */
		String CYCLE_DATE = "cycle_date";
		/** Type: INTEGER NOT NULL DEFAULT 0 */
		String PHOTO_URL = "photo_url";
	}

	public static class Dogs implements ItemsColumns {
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.gh0straider.dogtracker.dogs";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.gh0straider.dogtracker.dogs";

		public static final String DEFAULT_SORT = BIRTH_DATE + " DESC";

		/** Matches: /dogs/ */
		public static Uri buildDirUri() {
			return BASE_URI.buildUpon().appendPath("dogs").build();
		}

		/** Matches: /dogs/[_id]/ */
		public static Uri buildItemUri(long _id) {
			return BASE_URI.buildUpon().appendPath("dogs").appendPath(Long.toString(_id)).build();
		}

		/** Read item ID item detail URI. */
		public static long getItemId(Uri itemUri) {
			return Long.parseLong(itemUri.getPathSegments().get(1));
		}
	}

	private DogsContract() {
	}
}
