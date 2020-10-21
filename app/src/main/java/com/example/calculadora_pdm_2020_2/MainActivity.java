package com.example.calculadora_pdm_2020_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView visorTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visorTv = findViewById(R.id.visorTv);
    }

    public void onClick(View view) {
        StringBuilder sb = new StringBuilder(visorTv.getText());
        switch (view.getId()){
            case R.id.zeroBt:
                sb.append(getString(R.string.zero));
                Log.v(getString(R.string.app_name), getString(R.string.zero));
                break;
            case R.id.umBt:
                sb.append(getString(R.string.um));
                Log.v(getString(R.string.app_name), getString(R.string.um));
                break;
            case R.id.doisBt:
                sb.append(getString(R.string.dois));
                Log.v(getString(R.string.app_name), getString(R.string.dois));
                break;
            case R.id.tresBt:
                sb.append(getString(R.string.tres));
                Log.v(getString(R.string.app_name), getString(R.string.tres));
                break;
            case R.id.quatroBt:
                sb.append(getString(R.string.quatro));
                Log.v(getString(R.string.app_name), getString(R.string.quatro));
                break;
            case R.id.cincoBt:
                sb.append(getString(R.string.cinco));
                Log.v(getString(R.string.app_name), getString(R.string.cinco));
                break;
            case R.id.seisBt:
                sb.append(getString(R.string.seis));
                Log.v(getString(R.string.app_name), getString(R.string.seis));
                break;
            case R.id.seteBt:
                sb.append(getString(R.string.sete));
                Log.v(getString(R.string.app_name), getString(R.string.sete));
                break;
            case R.id.oitoBt:
                sb.append(getString(R.string.oito));
                Log.v(getString(R.string.app_name), getString(R.string.oito));
                break;
            case R.id.noveBt:
                sb.append(getString(R.string.nove));
                Log.v(getString(R.string.app_name), getString(R.string.nove));
                break;
            case R.id.backspaceBt:
                if (sb.length() > 0) { sb.setLength(sb.length() - 1); }
                Log.v(getString(R.string.app_name), "Apagado ultimo adicionado");
                break;
            case R.id.apagarBt:
                sb.setLength(0);
                Log.v(getString(R.string.app_name), "Tela limpa");
                break;
        }
        visorTv.setText(sb.toString());
    }
}