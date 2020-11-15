package com.example.calculadora_pdm_2020_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity : AppCompatActivity() {
    private lateinit var configuracoes: Configuracoes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        configuracoes = intent.getParcelableExtra(MainActivity.EXTRA_CONFIGURACOES) ?: Configuracoes(false)
        tipoCalculadoraRg.check(if (configuracoes.avancada) R.id.avancadoRb else R.id.basicaRb)
    }

    fun onClick(view: View) {
        if (view.id == R.id.salvarBt){
            configuracoes.avancada = avancadoRb.isChecked
            val resultadoIntent = Intent()
            resultadoIntent.putExtra(MainActivity.EXTRA_CONFIGURACOES, configuracoes)
            setResult(RESULT_OK, resultadoIntent)

            finish()
        }
    }
}