package com.example.imc01;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity {
	
	//private final String defaut = "toto";

	Button calculerIMC = null; 	
	Button raz = null; 
	
	EditText poids = null;		
	EditText taille = null;
	
	TextView result = null;
	RadioGroup group = null;
	
	float imc = 0;
	
	//IMC02
	Button interpretation = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{ 
		super.onCreate(savedInstanceState); //super. permet d'en r�f�rer � une m�thode m�re
		setContentView(R.layout.activity_main);
		
		//Les Boutons
		calculerIMC = (Button)findViewById(R.id.calcul); //D�finition du bouton Calculer
		interpretation = (Button)findViewById(R.id.interpretation); //D�finition du bouton interpretation
		raz 	=(Button)findViewById(R.id.raz); //D�finition du bouton qui ser � r�initialiser
		
		//Les champs de saisie
		taille	=(EditText)findViewById(R.id.taille); //D�finition de la zone de texte taille
		poids 	=(EditText)findViewById(R.id.poids); //D�finition de la zone de texte poids
		
		//Les bouton radio
		group 	=(RadioGroup)findViewById(R.id.group); //D�finition du groupe de bouton radio
		
		
		
		//On affecte un listener adapt� aux �v�nements qui peuvent se produire
		calculerIMC.setOnClickListener(envoyerListener);
		raz.setOnClickListener(razListener);
		taille.addTextChangedListener(textWatcher);
		poids.addTextChangedListener(textWatcher);
		
		
		
		//On acc�de 
		interpretation.setOnClickListener(interpretationListener);
	}

	//Ev�nement onClick sur le bouton "calculerIMC"
private OnClickListener envoyerListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		//On verifie si un des champ est vide
		if (taille.getText().toString().isEmpty() || poids.getText().toString().isEmpty()){
			
			//Message d'erreur si un des champs est vide.
			Resources message = getResources();
			Toast.makeText(MainActivity.this, message.getString(R.string.toast_message_erreur_saisie), Toast.LENGTH_LONG).show();
		}
		else{ //Les deux champs ont �t� compl�t�
		
			String t = taille.getText().toString(); //On r�cup�re la taille saisie par l'utilisateur et on l'affecte � la variable t
			String p = poids.getText().toString(); //On r�cup�re le poid saisie par l'utilisateur et on l'affecte � la variable p
			float tValue = Float.valueOf(t); //On transforme la valeur que contient t en float et on l'affecte � la variable tValue
			float pValue = Float.valueOf(p); //On trans
			
			if(group.getCheckedRadioButtonId()== R.id.radio2) //Si la taille est en centim�tre
			{
				tValue = tValue/100; //On convertie la taille en m�tre.
			}
			tValue = (float)Math.pow(tValue, 2); 
			imc = pValue/tValue; //Calcule de l'IMC
			imc = (float)Math.round(imc*100)/100; //Arrondie � 2 chiffres apr�s la virgule.
			
			Intent i ; // d�claration d'une variable i, de type Intent
			i = new Intent(getApplicationContext(), Conseil.class);
			i.putExtra("imc", String.valueOf(imc)); 
			startActivity(i); //lance l'activit� conseil 
		}
	}
};

private OnClickListener razListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		poids.getText().clear();
		taille.getText().clear();
	}
};


private OnClickListener interpretationListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		Intent i ; // d�claration d'une variable i, de type Intent
		i = new Intent(getApplicationContext(), Interpretation.class);
		 startActivity(i); // Interpretation est le nom de la classe associ�e � l'activit� d'affichage de l'interpr�tation de l'IMC. 
	}
};

private TextWatcher textWatcher = new TextWatcher()
 {
	 @Override
	 public void onTextChanged(CharSequence s, int start, int before, int count)
	 {
		 //result.setText(defaut);
	 }
	 
	 @Override
	 public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	 
	 @Override
	 public void afterTextChanged(Editable s){}

 };
 


//Savegarde et restauration
@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
    Log.w("P2", "onSaveInstanceState");
    savedInstanceState.putFloat("imc", imc);
    super.onSaveInstanceState(savedInstanceState);
}

@Override
public void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    imc = savedInstanceState.getFloat("imc");
    //result.setText(Float.toString(savedInstanceState.getFloat("imc")));
    Log.w("P2", "onRestoreInstanceState");
}
}

