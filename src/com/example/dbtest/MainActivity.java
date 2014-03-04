package com.example.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btnAddBook = (Button) findViewById(R.id.button1);
		Button btnList = (Button) findViewById(R.id.button2);
		
		btnAddBook.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				MySQLiteHelper dbHelper = new MySQLiteHelper(view.getContext());
				EditText author = (EditText) findViewById(R.id.author);
				EditText title = (EditText) findViewById(R.id.title);
				if (dbHelper.addBook(title.getText().toString(), author.getText().toString())) {
					Toast.makeText(getApplicationContext(), "Successsfully saved :)", Toast.LENGTH_SHORT).show();
					Intent resultIntent = new Intent(view.getContext(), ResultActivity.class);
					startActivity(resultIntent);
				}else {
					Toast.makeText(getApplicationContext(), "Something went wrong, try again :(", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		btnList.setOnClickListener(new Button.OnClickListener(){
			
			@Override
			public void onClick(View view) {
				Intent resultIntent = new Intent(view.getContext(), ResultActivity.class);
				startActivity(resultIntent);
			}
			
			
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
