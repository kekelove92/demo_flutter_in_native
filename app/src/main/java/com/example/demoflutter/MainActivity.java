package com.example.demoflutter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends AppCompatActivity {
    public static final String FLUTTER_ENGINE_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlutterEngine flutterEngine = new FlutterEngine(this);
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());

        FlutterEngineCache.getInstance().put(FLUTTER_ENGINE_ID, flutterEngine);

        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor(), "config");
        channel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                System.out.println("TAG " + call.method);
                System.out.println("TAG " + call.arguments());

                if (call.method.equals("set_config")){
                    JSONObject json = new JSONObject();
                    try {
                        json.put("partner_code",""); // partner code vps
                        json.put("color","#01AD52"); // hex color
                        json.put("device_type",""); // device type
                        json.put("logo",""); // logo/banner
                        json.put("logo_ratio",1125/396.0); // ratio for logo/banner
                    } catch (JSONException e) {
                        System.out.println("TAG " + e);
                    }
                    result.success(json.toString());
                }
            }
        });

        startActivity(FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this));
    }
}