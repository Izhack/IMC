package com.example.imc01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Interpretation extends Activity {

	//D�claration des propri�t�s
	Button quitter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interpretation);
		
		// d�claration du bouton d'action quitter et association avec un listener 
		quitter = (Button)findViewById(R.id.quitter);
		quitter.setOnClickListener(btnQuitter);
		
	}
		private OnClickListener btnQuitter = new OnClickListener()
		{
			public void onClick(View v)
			 {
				 Intent i = new Intent ();
				 setResult (Activity.RESULT_OK, i);
				 finish(); // arr�t de l'activit� ==> on revient � l'activit� pr�c�dente
			 }
		 };
				
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.interpretation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
