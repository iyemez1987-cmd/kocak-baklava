package com.posim.kocakbaklava;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.webkit.WebView;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

// Sayfadaki window.print() Android'de çalışmaz; bu eklenti o anki sayfayı
// (yazdırma görünümüyle) Android'in yazdırma servisine gönderir.
@CapacitorPlugin(name = "Yazdir")
public class YazdirPlugin extends Plugin {
    @PluginMethod
    public void yazdir(final PluginCall call) {
        final String ad = call.getString("ad", "Koçak Baklava");
        getActivity().runOnUiThread(() -> {
            try {
                WebView wv = getBridge().getWebView();
                PrintManager pm = (PrintManager) getActivity().getSystemService(Context.PRINT_SERVICE);
                PrintDocumentAdapter adaptor = wv.createPrintDocumentAdapter(ad);
                pm.print(ad, adaptor, new PrintAttributes.Builder().build());
                call.resolve();
            } catch (Exception e) {
                call.reject(e.getMessage());
            }
        });
    }
}
