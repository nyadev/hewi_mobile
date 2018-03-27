package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.TabHost;

public class citas extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        //Ocultamos la toolbar
        getActionBar().hide();

        Resources recursos = getResources();
        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("TabRegistrar");
        spec.setContent(R.id.tab1);
        spec.setIndicator("REGISTRAR");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("TabConsultar");
        spec.setContent(R.id.tab2);
        spec.setIndicator("CONSULTAR");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("TabEliminar");
        spec.setContent(R.id.tab3);
        spec.setIndicator("ELIMINAR");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }
}
