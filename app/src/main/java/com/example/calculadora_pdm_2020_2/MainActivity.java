package com.example.calculadora_pdm_2020_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


public class MainActivity extends AppCompatActivity {
    private final String VALOR_VISOR_TV = "valor_visor_tv";
    private final String VALOR_MEMORIA_TV = "valor_memoria_tv";
    private final String VALOR_OPERADOR_TV = "valor_operador_tv";

    private final int CALL_PHONE_PERMISSION_REQUEST_CODE = 0;
    private final int CONFIGURACOES_REQUEST_CODE = 1;

    public static final String EXTRA_CONFIGURACOES = "EXTRA_CONFIGURACOES";

    private TextView visorTv, memoriaTv, operadorTv;

    private Configuracoes configuracoes = new Configuracoes(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        visorTv = findViewById(R.id.visorTv);
        memoriaTv = findViewById(R.id.memoriaTv);
        operadorTv = findViewById(R.id.operadorTv);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VALOR_VISOR_TV, visorTv.getText().toString());
        outState.putString(VALOR_MEMORIA_TV, memoriaTv.getText().toString());
        outState.putString(VALOR_OPERADOR_TV, operadorTv.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        visorTv.setText(savedInstanceState.getString(VALOR_VISOR_TV, ""));
        memoriaTv.setText(savedInstanceState.getString(VALOR_MEMORIA_TV, ""));
        operadorTv.setText(savedInstanceState.getString(VALOR_OPERADOR_TV, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configuracoesMi:
//                Intent configuracoesIntent = new Intent(this, ConfiguracoesActivity.class);
//                startActivity(configuracoesIntent);
                Intent configuracoesIntent = new Intent("CONFIGURACOES");
                configuracoesIntent.putExtra(EXTRA_CONFIGURACOES, configuracoes);
                startActivityForResult(configuracoesIntent, CONFIGURACOES_REQUEST_CODE);
                return true;
            case R.id.siteIfspMi:
                Uri siteIfspUri = Uri.parse("https://www.ifsp.edu.br");
                Intent siteIfspIntent = new Intent(Intent.ACTION_VIEW, siteIfspUri);
                startActivity(siteIfspIntent);
                return true;
            case R.id.chamarIfspMi:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
                    }
                }
                chamarIfsp();
                return true;
            case R.id.sairMi:
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIGURACOES_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            configuracoes = data.getParcelableExtra(EXTRA_CONFIGURACOES);
            if (configuracoes != null && configuracoes.getAvancada()) {
                findViewById(R.id.configAvancadaLL).setVisibility(View.VISIBLE);
            }
            else {
                findViewById(R.id.configAvancadaLL).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE) {
            for (int resultado: grantResults) {
                if (resultado != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permissão necessária não concedida", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            chamarIfsp();
        }
    }

    private void chamarIfsp(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Uri chamarIfspUri = Uri.parse("tel:1137754501");
                Intent chamarIfspIntent = new Intent(Intent.ACTION_CALL, chamarIfspUri);
                startActivity(chamarIfspIntent);
            }
        }
    }

    public void onClick(View view) {
        StringBuilder sb = new StringBuilder(visorTv.getText());
        if (sb.toString().equals("Erro") || sb.toString().equals("∞")) {
            sb.setLength(0);
        }
        switch (view.getId()){
            case R.id.zeroBt:
                sb.append(getString(R.string.zero));
                break;
            case R.id.umBt:
                sb.append(getString(R.string.um));
                break;
            case R.id.doisBt:
                sb.append(getString(R.string.dois));
                break;
            case R.id.tresBt:
                sb.append(getString(R.string.tres));
                break;
            case R.id.quatroBt:
                sb.append(getString(R.string.quatro));
                break;
            case R.id.cincoBt:
                sb.append(getString(R.string.cinco));
                break;
            case R.id.seisBt:
                sb.append(getString(R.string.seis));
                break;
            case R.id.seteBt:
                sb.append(getString(R.string.sete));
                break;
            case R.id.oitoBt:
                sb.append(getString(R.string.oito));
                break;
            case R.id.noveBt:
                sb.append(getString(R.string.nove));
                break;
            case R.id.backspaceBt:
                if (sb.length() > 0) { sb.setLength(sb.length() - 1); }
                break;
            case R.id.eraseBt:
                sb.setLength(0);
                break;
            case R.id.eraseAllBt:
                sb.setLength(0);
                changeMemoryValues("", "");
                break;
            case R.id.plusMinusBt:
                if (sb.length() > 0) {
                    if (sb.charAt(0) == '-') {
                        sb.deleteCharAt(0);
                    } else {
                        sb.insert(0, '-');
                    }
                }
                break;
            case R.id.commaBt:
                if (sb.length() > 0 && sb.indexOf(getString(R.string.comma)) == -1) {
                    sb.append(getString(R.string.comma));
                }
                break;
            case R.id.plusBt:
                if (sb.length() > 0) {
                    if (operadorTv.getText().length() > 0) {
                        changeMemoryValues(calculateResult(sb.toString()), getString(R.string.plus));
                    }
                    else {
                        changeMemoryValues(sb.toString(), getString(R.string.plus));
                    }
                    sb.setLength(0);
                }
                break;
            case R.id.minusBt:
                if (sb.length() > 0) {
                    if (operadorTv.getText().length() > 0) {
                        changeMemoryValues(calculateResult(sb.toString()), getString(R.string.minus));
                    }
                    else {
                        changeMemoryValues(sb.toString(), getString(R.string.minus));
                    }
                    sb.setLength(0);
                }
                break;
            case R.id.multiplicationBt:
                if (sb.length() > 0) {
                    if (operadorTv.getText().length() > 0) {
                        changeMemoryValues(calculateResult(sb.toString()), getString(R.string.multiplication));
                    }
                    else {
                        changeMemoryValues(sb.toString(), getString(R.string.multiplication));
                    }
                    sb.setLength(0);
                }
                break;
            case R.id.divisionBt:
                if (sb.length() > 0) {
                    if (operadorTv.getText().length() > 0) {
                        changeMemoryValues(calculateResult(sb.toString()), getString(R.string.division));
                    }
                    else {
                        changeMemoryValues(sb.toString(), getString(R.string.division));
                    }
                    sb.setLength(0);
                }
                break;
            case R.id.equalsBt:
                if (sb.length() > 0 && operadorTv.getText().length() > 0) {
                    String result = calculateResult(sb.toString());
                    sb.setLength(0);
                    sb.append(result);
                    changeMemoryValues("", "");
                }
                break;
            case R.id.raizQuadradaBt:
                if (sb.length() > 0) {
                    NumberFormat nf = new DecimalFormat("#.############################");
                    Double result = Math.sqrt(stringToNumber(sb.toString(), nf));
                    sb.setLength(0);
                    sb.append(nf.format(result).replaceAll("\\.", ","));
                }
                break;
            case R.id.potenciaBt:
                if (sb.length() > 0) {
                    if (operadorTv.getText().length() > 0) {
                        changeMemoryValues(calculateResult(sb.toString()), getString(R.string.potencia));
                    }
                    else {
                        changeMemoryValues(sb.toString(), getString(R.string.potencia));
                    }
                    sb.setLength(0);
                }
                break;
        }
        if (sb.toString().equals("NaN")) {
            sb.setLength(0);
            sb.append("Erro");
        }
        visorTv.setText(sb.toString());
    }

    private String calculateResult(String primaryValue) {
        Double result = 0.0;
        NumberFormat nf = new DecimalFormat("#.############################");
        switch (operadorTv.getText().toString()) {
            case "+":
                result = stringToNumber(memoriaTv.getText().toString(), nf)
                        + stringToNumber(primaryValue, nf);
                break;
            case "−":
                result = stringToNumber(memoriaTv.getText().toString(), nf)
                        - stringToNumber(primaryValue, nf);
                break;
            case "÷":
                result = stringToNumber(memoriaTv.getText().toString(), nf)
                        / stringToNumber(primaryValue, nf);
                break;
            case "×":
                result = stringToNumber(memoriaTv.getText().toString(), nf)
                        * stringToNumber(primaryValue, nf);
                break;
            case "^":
                result = Math.pow(stringToNumber(memoriaTv.getText().toString(), nf),
                        stringToNumber(primaryValue, nf));
        }
        return nf.format(result).replaceAll("\\.", ",");
    }

    private Double stringToNumber(String value, NumberFormat nf) {
        try {
            return nf.parse(value.replaceAll(",", ".")).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private void changeMemoryValues(String value, String operador) {
        memoriaTv.setText(value);
        operadorTv.setText(operador);
    }
}