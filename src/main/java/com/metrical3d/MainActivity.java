package com.metrical3d;                                                                                            //Package

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

public class MainActivity extends Activity {

    final Context context = this;
    private Button btnCalculate;                                                                                   //Creates new variables
    private EditText TextCost;                                                                                     //Creates new variables
    private EditText TextLength;                                                                                   //Creates new variables
    private EditText TextDiameter;                                                                                 //Creates new variables
    private EditText TextTotal;                                                                                    //Creates new variables
    private EditText TextWeight;                                                                                   //Creates new variables
    private String StringDensity;                                                                                  //Creates new variables
    private RadioButton RadButtonABS;                                                                              //Creates new variables
    private RadioButton RadButtonPLA;                                                                              //Creates new variables
    private RadioButton RadButtonHDPE;                                                                             //Creates new variables
    private RadioButton RadButtonPVA;                                                                              //Creates new variables
    private RadioButton RadButtonPC;                                                                               //Creates new variables
    private RadioButton RadButtonT618;                                                                             //Creates new variables
    private ProgressBar Progress;                                                                                  //Creates new variables
    private TextView TextPercent;                                                                                  //Creates new variables

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = (Button)findViewById(R.id.btnStart);
        btnCalculate.setOnClickListener(buttonAddOnClickListener);
    }                                                       //Do Not change, Launches GUI

    Button.OnClickListener buttonAddOnClickListener  = new Button.OnClickListener(){    //Defines Button as action

        public void onClick(View arg0) {                                                 //Handles the action
            TextCost = (EditText)findViewById(R.id.editText4);                                                     //Assigns Variables to GUI Items
            TextLength = (EditText)findViewById(R.id.editText2);                                                    //Assigns Variables to GUI Items
            TextDiameter = (EditText)findViewById(R.id.editText);                                                 //Assigns Variables to GUI Items
            TextTotal = (EditText)findViewById(R.id.editText3);                                                    //Assigns Variables to GUI Items
            TextWeight = (EditText)findViewById(R.id.editText5);                                                   //Assigns Variables to GUI Items
            RadButtonABS = (RadioButton)findViewById(R.id.RadButtonABS);                                           //Assigns Variables to GUI Items
            RadButtonPLA = (RadioButton)findViewById(R.id.RadButtonPLA);                                           //Assigns Variables to GUI Items
            RadButtonHDPE = (RadioButton)findViewById(R.id.RadButtonHDPE);                                         //Assigns Variables to GUI Items
            RadButtonPVA = (RadioButton)findViewById(R.id.RadButtonPVA);                                           //Assigns Variables to GUI Items
            RadButtonPC = (RadioButton)findViewById(R.id.RadButtonPC);                                             //Assigns Variables to GUI Items
            RadButtonT618 = (RadioButton)findViewById(R.id.RadButtonT618);                                         //Assigns Variables to GUI Items
            Progress = (ProgressBar)findViewById(R.id.progressBar);                                                //Assigns Variables to GUI Items
            TextPercent = (TextView)findViewById(R.id.textView7);                                                  //Assigns Variables to GUI Items
            StringDensity = "0";                                                                                   //Assigns Blank Number to Variable

            if(TextDiameter.getText().toString().length() < 1){                                                    //Detects if TextDiameter is empty
                TextDiameter.setError("You must have at least 1 character in your filament diameter field");       //Creates error box in TextDiameter
                return;                                                                                            //Stops the Button Click Aftermath
            }
            else if(TextLength.getText().toString().length() < 1){                                                 //Detects if TextLength is empty
                TextLength.setError("You must have at least 1 character in your filament length field");           //Creates error box in TextLength
                return;                                                                                            //Stops the Button Click Aftermath
            }
            else if(TextCost.getText().toString().length() < 1){                                                   //Detects if TextCost is empty
                TextCost.setError("You must have at least 1 character in your filament cost field");               //Creates error box in TextCost
                return;                                                                                            //Stops the Button Click Aftermath
            }
            else{

            float Cost = Float.parseFloat(TextCost.getText().toString());                                          //Converts Variable to String
            float Length = Float.parseFloat(TextLength.getText().toString());                                      //Converts Variable to String
            float Diameter = Float.parseFloat(TextDiameter.getText().toString());                                  //Converts Variable to String
            float Radius = Diameter / 2;                                                                           //Defines radius

            int IntWeight;                                                                                         //Defines IntWeight to String

            double DArea = (Radius * Radius * Math.PI);                                                            //Defines DArea

            float Area = (float) DArea;                                                                            //Converts DArea from double to float and returns as area

            if(RadButtonABS.isChecked()){                                                                          //Detects if RadButtonABS is checked
                StringDensity = "0.00105";                                                                         //Defines StringDensity if RadButtonABS is checked
            }
            if(RadButtonPLA.isChecked()){                                                                          //Detects if RadButtonPLA is checked
                StringDensity = "0.00125";                                                                         //Defines StringDensity if RadButtonPLA is checked
            }
            if(RadButtonHDPE.isChecked()){                                                                         //Detects if RadButtonHDPE is checked
                StringDensity = "0.00097";                                                                         //Defines StringDensity if RadButtonHDPE is checked
            }
            if(RadButtonPVA.isChecked()){                                                                          //Detects if RadButtonPVA is checked
                StringDensity = "0.00119";                                                                         //Defines StringDensity if RadButtonPVA is checked
            }
            if(RadButtonPC.isChecked()){                                                                           //Detects if RadButtonPC is checked
                StringDensity = "0.0012";                                                                          //Defines StringDensity if RadButtonPC is checked
            }
            if(RadButtonT618.isChecked()){                                                                         //Detects if RadButtonT618 is checked
                StringDensity = "0.001134";                                                                        //Defines StringDensity if RadButtonT618 is checked
            }

            float NStringDensity = Float.valueOf(StringDensity.trim()).floatValue();                               //converts StringDensity to float from String and returns as NStringDensity
            float weight = Area * Length * NStringDensity;                                                         //Defines weight
            float TotalCost = Cost / 1000 * weight;                                                                //Defines TotalCost
            float Percentage = weight / 10;                                                                        //Defines Percentage

            IntWeight=(int)weight;                                                                                 //Converts String to Integer

            Double DPercentage = Math.round(Percentage * 100)/100.00;                                              //Round Percentage to DPercentage
            Double NETotalCost = Math.round(TotalCost * 100)/100.00;                                               //Round TotalCost to NETotalCost

            String NEATotalCost = Double.toString(NETotalCost);                                                    //Converts NETotalCost from double to String and returns as NEATotalCost
            String DEPercentage = Double.toString(DPercentage);                                                    //Converts DPercentage from double to String and returns as DEPercentage

            if(weight > 1000 ){                                                                                    //Detects if weight is under the maximum
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);                         //Creates Message Box
                alertDialogBuilder.setTitle("Error!");                                                             //Sets Message Box Title
                alertDialogBuilder                                                                                 //Builds Message Box Information
                        .setMessage("Build weight exceeds 1kg!")                                                   //Sets Message Box Message
                        .setCancelable(false)                                                                      //Disables cancel button on Message Box
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {                         //Creates Button Okay
                            public void onClick(DialogInterface dialog, int id) {                                  //Defines Button Press
                                dialog.cancel();                                                                   //Breaks Message Box on Button Press
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();                                             //Builds Message Box
                alertDialog.show();                                                                                //Shows Message Box
            }

            TextTotal.setText("$" + NEATotalCost);                                                                 //Sets the GUI TextTotal as the TotalCost calculation
            TextWeight.setText(weight + "g");                                                                      //Sets the GUI TextWeight as the Weight calculation
            TextPercent.setText(DEPercentage + "%");                                                               //Sets the GUI TextPercentage as the TextProgress percentage
            Progress.setProgress(IntWeight);                                                                       //Sets the GUI ProgressBar as the ProgressBar percentage
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);                  //Defines Soft Keyboard
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);                                    //Hides Soft Keyboard
        }
        }
    };
}