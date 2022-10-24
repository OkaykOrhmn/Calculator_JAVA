package com.rhmn.calculatorjava;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.faendir.rhino_android.RhinoAndroidHelper;
import com.google.android.material.button.MaterialButton;
import com.rhmn.calculatorjava.databinding.ActivityMainBinding;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private Context context;
    private RhinoAndroidHelper rhinoAndroidHelper;

    private boolean rotate = false;
    private boolean inAdvance = false;

    private int lenthAdvance = 0;
    private int lenthAdvance0 = 0;
    private int isPower = 0;

    private double a = 0;
    private double b = 0;
    private static final String TAG = "KIANOOSH";

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_nights_stay_24);
                Log.d(TAG, "isDark: ");
                binding.switchThem.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_light_mode_24);
                Log.d(TAG, "isLight: ");
                binding.switchThem.setChecked(false);

                break;

        }


        binding.switchThem.setOnClickListener(view -> {
            if (binding.switchThem.isChecked()){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_nights_stay_24);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_light_mode_24);
            }
        });

        binding.rotate.setOnClickListener(view -> {

            if (rotate){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                Log.d(TAG, "rotateP1: "+rotate);
                rotate = false;
                Log.d(TAG, "rotateP2: "+rotate);

            }else{
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                Log.d(TAG, "rotateL1: " + rotate);
                rotate = true;
                Log.d(TAG, "rotateL2: " + rotate);
            }

        });



            assignId(binding.number0);
            assignId(binding.number1);
            assignId(binding.number2);
            assignId(binding.number3);
            assignId(binding.number4);
            assignId(binding.number5);
            assignId(binding.number6);
            assignId(binding.number7);
            assignId(binding.number8);
            assignId(binding.number9);


            assignId(binding.symbolPositive);
            assignId(binding.symbolNegative);
            assignId(binding.symbolX);
            assignId(binding.symbolDivide);
            assignId(binding.symbolPercent);
            assignId(binding.symbolDot);
            assignId(binding.symbolResult);
            assignId(binding.ac);
            assignId(binding.delete);

            if (binding.symbolCos != null){

                assignId(binding.symbolCos);
                assignId(binding.symbolTan);
                assignId(binding.symbolSin);
                assignId(binding.log);
                assignId(binding.symbolOpen);
                assignId(binding.symbolClose);
                assignId(binding.symbolPower);
                assignId(binding.symbolRadikal);
            }


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean rotate = this.rotate;
        outState.putBoolean("rotate", rotate);
        Log.d(TAG, "onSaveInstanceState: "+ rotate);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        rotate = savedInstanceState.getBoolean("rotate");
        Log.d(TAG, "onRestoreInstanceState: "+rotate);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                Log.d(TAG, "isDark: ");
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_nights_stay_24);
                binding.switchThem.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                Log.d(TAG, "isLight: ");
                binding.imageSwitch.setImageResource(R.drawable.ic_baseline_light_mode_24);
                binding.switchThem.setChecked(false);

                break;

        }

    }

    void assignId(MaterialButton button){
        button.setOnClickListener(this::onClick);


    }

    @SuppressLint({"SourceLockedOrientationActivity", "SetTextI18n", "ResourceAsColor"})
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = binding.calculatingTv.getText().toString();


        Log.d(TAG, "onClick: "+buttonText);

        //x
        if (buttonText.equals("×")){
            if (binding.calculatingTv.getText().toString().endsWith("*")){
                Log.d(TAG, "×: "+binding.calculatingTv.getText().toString());
                return;
            }
            binding.calculatingTv.setText(binding.calculatingTv.getText()+"*");
            return;
        }

        //÷
        if (buttonText.equals("÷")){
            if (binding.calculatingTv.getText().toString().endsWith("/")){
                Log.d(TAG, "÷: "+binding.calculatingTv.getText().toString());
                return;
            }
            binding.calculatingTv.setText(binding.calculatingTv.getText()+"/");
            return;
        }

        //+
        if (buttonText.equals("+")  ){
            if (binding.calculatingTv.getText().toString().endsWith("+")){
                Log.d(TAG, "+: "+binding.calculatingTv.getText().toString());
                return;
            }

        }

        //-
        if (buttonText.equals("-")  ){
            if (binding.calculatingTv.getText().toString().endsWith("-")){
                Log.d(TAG, "-: "+binding.calculatingTv.getText().toString());
                return;
            }

        }

        //%
        if (buttonText.equals("%")  ){
            if (binding.calculatingTv.getText().toString().endsWith("%")){
                Log.d(TAG, "%: "+binding.calculatingTv.getText().toString());
                return;
            }

        }

        //.
        if (buttonText.equals(".")  ){
            if (binding.calculatingTv.getText().toString().endsWith(".")){
                Log.d(TAG, ".: "+binding.calculatingTv.getText().toString());
                return;
            }

        }

        //AC
        if (buttonText.equals("AC")){
            binding.calculatingTv.setText("");
            binding.calculatingResult.setText("0");
            return;
        }

        //cos
        if (buttonText.equals("cos")){
            if (!inAdvance) {
                lenthAdvance = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("cos(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(inAdvance);
                binding.symbolCos.setClickable(!inAdvance);
                inAdvance = true;

            }else{
                binding.advance.setText("");

                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    setClickable(inAdvance);
                    inAdvance = false;

                }else {
                    double aDouble = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    double result = Math.cos(aDouble);
                    Log.d(TAG, "): " + result);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance)
                            + result);

                    setClickable(inAdvance);
                    inAdvance = false;
                }
            }
            return;
        }

        //sin
        if (buttonText.equals("sin")){
            if (!inAdvance) {
                lenthAdvance = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("sin(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(inAdvance);
                binding.symbolSin.setClickable(!inAdvance);
                inAdvance = true;

            }else{
                binding.advance.setText("");

                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    setClickable(inAdvance);
                    inAdvance = false;

                }else {
                    double aDouble = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    double result = Math.sin(aDouble);
                    Log.d(TAG, "): " + result);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance)
                            + result);

                    setClickable(inAdvance);
                    inAdvance = false;
                }
            }
            return;
        }

        //tan
        if (buttonText.equals("tan")){
            if (!inAdvance) {
                lenthAdvance = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("tan(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(inAdvance);
                binding.symbolTan.setClickable(!inAdvance);
                inAdvance = true;

            }else{
                binding.advance.setText("");

                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    setClickable(inAdvance);
                    inAdvance = false;

                }else {
                    double aDouble = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    double result = Math.tan(aDouble);
                    Log.d(TAG, "): " + result);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance)
                            + result);

                    setClickable(inAdvance);
                    inAdvance = false;
                }
            }
            return;
        }

        //log
        if (buttonText.equals("log")){
            if (!inAdvance) {
                lenthAdvance = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("log(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(inAdvance);
                binding.log.setClickable(!inAdvance);
                inAdvance = true;

            }else{
                binding.advance.setText("");

                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    setClickable(inAdvance);
                    inAdvance = false;

                }else {
                    double aDouble = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    double result = Math.log(aDouble);
                    Log.d(TAG, "): " + result);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance)
                            + result);

                    setClickable(inAdvance);
                    inAdvance = false;
                }
            }
            return;
        }

        //radical
        if (buttonText.equals("√")){
            if (!inAdvance) {
                lenthAdvance = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("√(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(inAdvance);
                binding.symbolRadikal.setClickable(!inAdvance);
                inAdvance = true;

            }else{
                binding.advance.setText("");
                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();

                    setClickable(inAdvance);
                    inAdvance = false;

                }else {
                    double aDouble = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    double result = Math.sqrt(aDouble);
                    Log.d(TAG, "): " + result);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance)
                            + result);

                    setClickable(inAdvance);
                    inAdvance = false;
                }
            }
            return;
        }

        //power
        if (buttonText.equals("xⁿ")){
            if (isPower == 0){
                lenthAdvance0 = binding.calculatingTv.getText().toString().length();
                binding.advance.setText("x(");
                Toast.makeText(this, "عدد را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();

                setClickable(false);
                binding.symbolPower.setClickable(true);
                isPower = 1;

            }else if(isPower == 1){
                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    binding.advance.setText("");
                    setClickable(true);
                    isPower = 0;

                }else {
                    a = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance0));
                    lenthAdvance = binding.calculatingTv.getText().toString().length();
                    binding.advance.setText("n(");
                    Toast.makeText(this, "توان را وارد کنید سپس دوباره کلیک کنید", Toast.LENGTH_SHORT).show();
                    isPower = 2;
                }

            }else if (isPower == 2 ){

                if (binding.calculatingTv.getText().equals("")|| binding.calculatingTv.getText().length() == lenthAdvance){
                    Toast.makeText(this, "عدد نمیتواند خالی باشد!", Toast.LENGTH_SHORT).show();
                    binding.advance.setText("");
                    setClickable(true);
                    isPower = 0;

                }else {
                    b = Double.parseDouble(binding.calculatingTv.getText().toString().substring(lenthAdvance));
                    binding.advance.setText("");
                    double result = Math.pow(a, b);

                    binding.calculatingTv.setText(binding.calculatingTv.getText().toString().substring(0, lenthAdvance0)
                            + result);
                    isPower = 0;

                    setClickable(true);
                }

            }

            return;

        }


        //=
        if (buttonText.equals("=")){
            binding.calculatingTv.setText(binding.calculatingResult.getText());
            return;
        }

        //delete
        if (button == binding.delete){
            Log.d(TAG, "delete: "+ dataToCalculate.length());

            if (dataToCalculate.length() ==0 ||
                    dataToCalculate.length() == 1){
                Log.d(TAG, "=0: "+ dataToCalculate.length());
                binding.calculatingTv.setText("");
                binding.calculatingResult.setText("0");
                return;

            }else{
                Log.d(TAG, "!=0: "+ dataToCalculate.length());

                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }

            if (inAdvance){
                if (binding.calculatingTv.length() <= lenthAdvance){
                    inAdvance = false;
                    binding.advance.setText("");
                }
            }

            if (isPower != 0){
                if (binding.calculatingTv.length() <= lenthAdvance0){
                    isPower = 0;
                    binding.advance.setText("");
                }
            }

        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        //0
        if(buttonText.equals("0")){
            if (dataToCalculate.length() == 1){
                binding.calculatingTv.setText("");
                return;
            }
        }

        //result
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            binding.calculatingResult.setText(finalResult);
        }

        binding.calculatingTv.setText(dataToCalculate);
    }

    String getResult(String data){
        try {
            rhinoAndroidHelper = new RhinoAndroidHelper(this);
            context = rhinoAndroidHelper.enterContext();
            context.setOptimizationLevel(1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

    public void setClickable(boolean b){
        binding.symbolCos.setClickable(b);
        binding.symbolSin.setClickable(b);
        binding.symbolTan.setClickable(b);
        binding.log.setClickable(b);
        binding.symbolRadikal.setClickable(b);
        binding.symbolPower.setClickable(b);
        binding.symbolOpen.setClickable(b);
        binding.symbolClose.setClickable(b);
    }
}