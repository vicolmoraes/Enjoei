package com.enjoei.vicolmoraes.enjoei.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.enjoei.vicolmoraes.enjoei.R;

public class ErroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erro_activity);

        setarBotao();
    }

    private void setarBotao() {
        Button tentarNovamente = findViewById(R.id.bt_erro_tentar);
        tentarNovamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), IndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
