package com.example.dbtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ResultActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		final List<HashMap<String, String>> list = getAllBooks();
		
		SimpleAdapter adapter = new SimpleAdapter(
												this,
												list, 
												R.layout.custom_row,
												new String[] {"title", "author"},
												new int[] {R.id.text1, R.id.text2});
		
//		populateList();
		setListAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_database, menu);
		return true;
	}
	
	public List<HashMap<String, String>> getAllBooks() {
		List<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		MySQLiteHelper db = new MySQLiteHelper(this);
		Cursor cursor = db.getAllBooks();
		if (cursor.moveToFirst()) {
			do {
				map = new HashMap<String, String>();
				map.put("id", cursor.getString(0).toString());
				map.put("title", cursor.getString(1).toString());
				map.put("author", cursor.getString(2).toString());
				myList.add(map);
			}while (cursor.moveToNext());
			db.close();
		}
		return myList;
	}
	
	protected void onListItemClick(ListView l, View view, int position, long id) {
	    super.onListItemClick(l, view, position, id);
	    Intent detailIntent = new Intent(view.getContext(), DetailActivity.class);
	    detailIntent.putExtra("id", position + 1);
		startActivity(detailIntent);
	}

}
