package com.example.dbtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleAdapter;

public class CopyOfResultActivity extends ListActivity {
	
	/*ListView lv;*/

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		final List<HashMap<String, String>> list = getAllBooks();
//		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.activity_result, 
//				new String[] {"author", "title"}, new int[] {R.id.textView1, R.id.textView2});
//		lv.setAdapter(adapter);
		
		SimpleAdapter adapter = new SimpleAdapter(
												this,
												list, 
												R.layout.custom_row,
												new String[] {"title", "author"},
												new int[] {R.id.text2, R.id.text1});
		
//		populateList();
		setListAdapter(adapter);
		
	}
	
//	static final ArrayList<HashMap<String, String>> list = 
//			new ArrayList<HashMap<String, String>>();
	
//	private void populateList() {
//		MySQLiteHelper db = new MySQLiteHelper(this);
//		Cursor cursor = db.getAllBooks();
//		HashMap<String,String> temp = new HashMap<String,String>();
//		if (cursor.moveToFirst()) {
//			do {
//				temp.put("title", cursor.getString(0));
//				temp.put("author", cursor.getString(1));
//				list.add(temp);
//			}while (cursor.moveToNext());
//			db.close();
//		}
//	}

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
				map.put("title", cursor.getString(0).toString());
				map.put("author", cursor.getString(1).toString());
				myList.add(map);
			}while (cursor.moveToNext());
			db.close();
		}
		return myList;
	}

}
