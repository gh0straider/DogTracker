
package com.gh0straider.dogtracker.data;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class DogsProvider extends ContentProvider {
	private SQLiteOpenHelper mOpenHelper;

	interface Tables {
		String DOGS = "dogs";
	}

	private static final int DOGS = 0;
	private static final int DOGS__ID = 1;

	private static final UriMatcher sUriMatcher = buildUriMatcher();

	private static UriMatcher buildUriMatcher() {
		final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = DogsContract.CONTENT_AUTHORITY;
		matcher.addURI(authority, "dogs", DOGS);
		matcher.addURI(authority, "dogss/#", DOGS__ID);
		return matcher;
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new DogsDatabase(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		final int match = sUriMatcher.match(uri);
		switch (match) {
			case DOGS:
				return DogsContract.Items.CONTENT_TYPE;
			case DOGS__ID:
				return DogsContract.Items.CONTENT_ITEM_TYPE;
			default:
				throw new UnsupportedOperationException("Unknown uri: " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		final SelectionBuilder builder = buildSelection(uri);
		Cursor cursor = builder.where(selection, selectionArgs).query(db, projection, sortOrder);
		if (cursor != null) {
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
		}
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final int match = sUriMatcher.match(uri);
		switch (match) {
			case DOGS: {
				final long _id = db.insertOrThrow(Tables.DOGS, null, values);
				getContext().getContentResolver().notifyChange(uri, null);
				return DogsContract.Items.buildItemUri(_id);
			}
			default: {
				throw new UnsupportedOperationException("Unknown uri: " + uri);
			}
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final SelectionBuilder builder = buildSelection(uri);
		getContext().getContentResolver().notifyChange(uri, null);
		return builder.where(selection, selectionArgs).update(db, values);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		final SelectionBuilder builder = buildSelection(uri);
		getContext().getContentResolver().notifyChange(uri, null);
		return builder.where(selection, selectionArgs).delete(db);
	}

	private SelectionBuilder buildSelection(Uri uri) {
		final SelectionBuilder builder = new SelectionBuilder();
		final int match = sUriMatcher.match(uri);
		return buildSelection(uri, match, builder);
	}

	private SelectionBuilder buildSelection(Uri uri, int match, SelectionBuilder builder) {
		final List<String> paths = uri.getPathSegments();
		switch (match) {
			case DOGS: {
				return builder.table(Tables.DOGS);
			}
			case DOGS__ID: {
				final String _id = paths.get(1);
				return builder.table(Tables.DOGS).where(DogsContract.Items._ID + "=?", _id);
			}
			default: {
				throw new UnsupportedOperationException("Unknown uri: " + uri);
			}
		}
	}

	/**
	 * Apply the given set of {@link ContentProviderOperation}, executing inside
	 * a {@link SQLiteDatabase} transaction. All changes will be rolled back if
	 * any single one fails.
	 */
	public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations)
			throws OperationApplicationException {
		final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		db.beginTransaction();
		try {
			final int numOperations = operations.size();
			final ContentProviderResult[] results = new ContentProviderResult[numOperations];
			for (int i = 0; i < numOperations; i++) {
				results[i] = operations.get(i).apply(this, results, i);
			}
			db.setTransactionSuccessful();
			return results;
		} finally {
			db.endTransaction();
		}
	}
}
