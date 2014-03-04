package com.example.dbtest;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		int id = getIntent().getExtras().getInt("id");
//		Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
		TextView author = (TextView) findViewById(R.id.textView2);
		TextView title = (TextView) findViewById(R.id.textView4);
		
		MySQLiteHelper dbHelper = new MySQLiteHelper(this);
		Cursor cursor = dbHelper.getBookById(id);
		
		cursor.moveToFirst();
		
		title.setText(cursor.getString(2).toString());
		author.setText(cursor.getString(1).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
