package com.metrical3d;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    final Context context = this;
    private Button btnCalculate;                                                        //Creates new variables
    private EditText TextCost;                                                          //Creates new variables
    private EditText TextLength;                                                        //Creates new variables
    private EditText TextDiameter;                                                      //Creates new variables
    private EditText TextTotal;                                                         //Creates new variables
    private EditText TextWeight;                                                        //Creates new variables
    private String StringDensity;                                                       //Creates new variables
    private RadioButton RadButtonABS;                                                   //Creates new variables
    private RadioButton RadButtonPLA;                                                   //Creates new variables
    private RadioButton RadButtonHDPE;                                                  //Creates new variables
    private RadioButton RadButtonPVA;                                                   //Creates new variables
    private RadioButton RadButtonPC;                                                    //Creates new variables
    private RadioButton RadButtonT618;                                                  //Creates new variables
    private ProgressBar Progress;                                                       //Creates new variables
    private TextView TextPercent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = (Button)findViewById(R.id.btnStart);
        btnCalculate.setOnClickListener(buttonAddOnClickListener);
    }                            //Do Not change, Launched GUI

    Button.OnClickListener buttonAddOnClickListener  = new Button.OnClickListener(){    //Defines Button as action

        public void onClick(View arg0) {                                                //Handles the action

            TextCost = (EditText)findViewById(R.id.editText2);                          //Assigns Variables to GUI Items
            TextLength = (EditText)findViewById(R.id.editText);                         //Assigns Variables to GUI Items
            TextDiameter = (EditText)findViewById(R.id.editText4);                      //Assigns Variables to GUI Items
            TextTotal = (EditText)findViewById(R.id.editText3);                         //Assigns Variables to GUI Items
            TextWeight = (EditText)findViewById(R.id.editText5);                        //Assigns Variables to GUI Items
            RadButtonABS = (RadioButton)findViewById(R.id.RadButtonABS);                //Assigns Variables to GUI Items
            RadButtonPLA = (RadioButton)findViewById(R.id.RadButtonPLA);                //Assigns Variables to GUI Items
            RadButtonHDPE = (RadioButton)findViewById(R.id.RadButtonHDPE);              //Assigns Variables to GUI Items
            RadButtonPVA = (RadioButton)findViewById(R.id.RadButtonPVA);                //Assigns Variables to GUI Items
            RadButtonPC = (RadioButton)findViewById(R.id.RadButtonPC);                  //Assigns Variables to GUI Items
            RadButtonT618 = (RadioButton)findViewById(R.id.RadButtonT618);              //Assigns Variables to GUI Items
            Progress = (ProgressBar)findViewById(R.id.progressBar);                     //Assigns Variables to GUI Items
            TextPercent = (TextView)findViewById(R.id.textView7);                       //Assigns Variables to GUI Items
            StringDensity = "0";                                                        //Assigns Blank Number to Variable

            float Cost = Float.parseFloat(TextCost.getText().toString());               //Converts Variable to String
            float Length = Float.parseFloat(TextLength.getText().toString());           //Converts Variable to String
            float Diameter = Float.parseFloat(TextDiameter.getText().toString());       //Converts Variable to String
            float Radius = Diameter / 2;                                                //Defines radius

            int IntWeight;                                                              //Defines IntWeight to String

            double DArea = (Radius * Radius * Math.PI);                                 //Defines DArea

            float Area = (float) DArea;                                                 //Converts DArea from double to float and returns as area

            if(RadButtonABS.isChecked()){
                StringDensity = "0.00105";                                              //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonPLA.isChecked()){
                StringDensity = "0.00125";                                              //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonHDPE.isChecked()){
                StringDensity = "0.00097";                                              //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonPVA.isChecked()){
                StringDensity = "0.00119";                                              //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonPC.isChecked()){
                StringDensity = "0.0012";                                              //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonT618.isChecked()){
                StringDensity = "0.001134";                                              //Defines StringDensity if RadButtonABS is checked
            }

            if (TextCost.getText().toString().equals("")) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Error!");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Build weight exceeds 1kg!")
                        .setCancelable(false)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        float NStringDensity = Float.valueOf(StringDensity.trim()).floatValue();    //converts StringDensity to float from String and returns as NStringDensity
            float weight = Area * Length * NStringDensity;                              //Defines weight
            float TotalCost = Cost / 1000 * weight;                                     //Defines TotalCost
            float Percentage = weight / 10;                                             //Defines Percentage

            IntWeight=(int)weight;                                                      //Converts String to Integer

            Double DPercentage = Math.round(Percentage * 100)/100.00;                   //Converts TotalCost from Float to Double and rounds the number and returns as NEtotalCost
            Double NETotalCost = Math.round(TotalCost * 100)/100.00;                    //Converts TotalCost from Float to Double and rounds the number and returns as NEtotalCost

            String NEATotalCost = Double.toString(NETotalCost);                         //Converts NETotalCost from double to String and returns as NEATotalCost
            String DEPercentage = Double.toString(DPercentage);                         //Converts NETotalCost from double to String and returns as NEATotalCost

            if(weight > 1000 ){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Error!");
                // set dialog message
                alertDialogBuilder
                        .setMessage("Build weight exceeds 1kg!")
                        .setCancelable(false)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

            TextTotal.setText("$" + NEATotalCost);                                      //Sets the GUI TextTotal as the TotalCost calculation
            TextWeight.setText(weight + "g");                                           //Sets the GUI TextWeight as the Weight calculation
            TextPercent.setText(DEPercentage + "%");                                    //Sets the GUI TextPercentage as the percentage
            Progress.setProgress(IntWeight);                                            //Sets the GUI ProgressBAr as the the percentage
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    };
}