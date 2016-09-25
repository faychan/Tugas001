package id.sch.smktelkom_mlg.tugas01.xirpl1013.registrationfaych_animecorp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etName, etYear;
    Spinner spProvince, spCity;
    RadioGroup rgJK;
    CheckBox cbSMA, cbMHS, cbKRJ;
    Button bOk;
    TextView tvResult1, tvResult2, tvResult3, tvResult4, tvEducation;
    int nSkills;

    String[][] arCity = {{"West Jakarta", "Central Jakarta", "South Jakarta", "East Jakarta", "North Jakarta"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang", "Magelang", "Surakarta"},
            {"Yogyakarta", "Sleman", "Bantul", "Kulon Progo", "Gunung Kidul"},
            {"Surabaya", "Malang", "Pasuruan",}, {"Denpasar"}};
    ArrayList<String> listCity = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.Name);
        etYear = (EditText) findViewById(R.id.Year);
        spProvince = (Spinner) findViewById(R.id.spinnerProvince);
        spCity = (Spinner) findViewById(R.id.spinnerCity);
        rgJK = (RadioGroup) findViewById(R.id.radioGroupJK);
        cbSMA = (CheckBox) findViewById(R.id.checkBoxSMA);
        cbMHS = (CheckBox) findViewById(R.id.checkBoxMHS);
        cbKRJ = (CheckBox) findViewById(R.id.checkBoxKRJ);
        bOk = (Button) findViewById(R.id.buttonPOST);

        cbSMA.setOnCheckedChangeListener(this);
        cbMHS.setOnCheckedChangeListener(this);
        cbKRJ.setOnCheckedChangeListener(this);

        tvResult1 = (TextView) findViewById(R.id.textViewResult1);
        tvResult2 = (TextView) findViewById(R.id.textViewResult2);
        tvResult3 = (TextView) findViewById(R.id.textViewResult3);
        tvResult4 = (TextView) findViewById(R.id.textViewResult4);
        tvEducation = (TextView) findViewById(R.id.textViewEducation);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCity);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapter);

        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listCity.clear();
                listCity.addAll(Arrays.asList(arCity[pos]));
                adapter.notifyDataSetChanged();
                spCity.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.buttonPOST).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });
    }

    private void doClick() {
        String name = etName.getText().toString();
        String year = etYear.getText().toString();
        String result2 = "Last Education   : ";
        String result3 = null;
        int hm = result2.length();

        if (name.isEmpty()) {
            etName.setError("Name is not yet filled");
        } else if (name.length() <= 3) {
            etName.setError("Name needs to be more than 3");
        } else {
            etName.setError(null);
        }

        if (year.isEmpty()) {
            etYear.setError("Birth date is not filled");
        } else if (year.length() != 10) {
            etYear.setError("Birth date format is incorrect");
        } else {
            etYear.setError(null);
        }
        tvResult1.setText("Name                   : " + name + "\nBirth date             : " + year);
        tvResult2.setText("Provenance         : " + "City " + spCity.getSelectedItem().toString() + ", "
                + spProvince.getSelectedItem().toString());
        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgJK.getCheckedRadioButtonId());
            result3 = rb.getText().toString();
        }
        if (result3 == null) {
            tvResult3.setText("Gender                 : -");
        } else {
            tvResult3.setText("Gender                 : " + result3);
        }

        if (cbSMA.isChecked()) result2 += cbSMA.getText() + ", ";
        if (cbMHS.isChecked()) result2 += cbMHS.getText() + ", ";
        if (cbKRJ.isChecked()) result2 += cbKRJ.getText() + ". ";

        if (result2.length() == hm) result2 += "Other";
        tvResult4.setText(result2);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nSkills += 1;
        else nSkills -= 1;

        tvEducation.setText("Education");
    }
}

