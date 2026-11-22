package com.posim.kocakbaklava;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(YazdirPlugin.class);
        super.onCreate(savedInstanceState);
    }
}
