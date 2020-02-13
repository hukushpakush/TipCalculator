package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TextWatcher, SeekBar.OnSeekBarChangeListener {

    private EditText amountInput;

    private TextView percentageView, tipAmountView, totalAmountView;

    private SeekBar seekBar;

    private double percent = .15;
    private double billAmount;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountInput     = (EditText) findViewById(R.id.billAmount);
        percentageView  = (TextView) findViewById(R.id.percentage);
        tipAmountView   = (TextView) findViewById(R.id.tipAmount);
        totalAmountView = (TextView) findViewById(R.id.totalBillAmount);
        seekBar         = (SeekBar) findViewById(R.id.seekBar);

        amountInput.addTextChangedListener(this);
        seekBar.setOnSeekBarChangeListener(this);
    }

    private void calculate() {
        percentageView.setText(percentFormat.format(percent));
        if(amountInput.getText().toString().isEmpty()) {
            setTexts(0,0);
            return;
        }

        billAmount = Double.parseDouble(amountInput.getText().toString());
        double tip =  billAmount * percent;
        double total =  billAmount + tip;
        setTexts(tip,total);

    }

    private void setTexts(double tip, double total) {
        tipAmountView.setText(currencyFormat.format(tip));
        totalAmountView.setText(currencyFormat.format(total));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        calculate();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        percent = (double) progress/100;
        calculate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
