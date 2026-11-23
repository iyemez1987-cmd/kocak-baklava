package com.posim.kocakbaklava;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(YazdirPlugin.class);
        super.onCreate(savedInstanceState);

        WebView wv = getBridge().getWebView();
        // Telefonun büyük yazı ayarı sayfayı şişirmesin; ekran düzeni her telefonda aynı kalsın
        wv.getSettings().setTextZoom(100);

        // Sayfa saat/pil çubuğunun ve alttaki gezinme çubuğunun altına girmesin
        View kok = (View) wv.getParent();
        if (kok != null) kok.setBackgroundColor(Color.parseColor("#1E3A2A"));
        ViewCompat.setOnApplyWindowInsetsListener(wv, (v, ins) -> {
            Insets i = ins.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout()
                    | WindowInsetsCompat.Type.ime());
            ViewGroup.LayoutParams lp = v.getLayoutParams();
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams m = (ViewGroup.MarginLayoutParams) lp;
                m.setMargins(i.left, i.top, i.right, i.bottom);
                v.setLayoutParams(m);
            }
            return WindowInsetsCompat.CONSUMED;
        });
        WindowInsetsControllerCompat c = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        c.setAppearanceLightStatusBars(false);
        c.setAppearanceLightNavigationBars(false);
        ViewCompat.requestApplyInsets(wv);
    }
}
