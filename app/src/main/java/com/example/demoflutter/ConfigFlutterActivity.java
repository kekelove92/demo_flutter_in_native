package com.example.demoflutter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.util.GeneratedPluginRegister;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class ConfigFlutterActivity extends FlutterActivity {

    private static final String CHANNEL_CONFIG = "config";
    public static final int FLUTTER_ENGINE_ID = 1;
    public static final String BANNER_URL = "https://www.freepnglogos.com/uploads/google-logo-png/google-logo-png-webinar-optimizing-for-success-google-business-webinar-13.png";
    private MethodChannel.Result result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        GeneratedPluginRegister.registerGeneratedPlugins(flutterEngine);
        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL_CONFIG);
        channel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                System.out.println("TAG " + call.method);
                System.out.println("TAG " + call.arguments());

                if (call.method.equals("set_config")){
                    JSONObject json = new JSONObject();
                    try {
                        json.put("partner_code",""); // partner code
                        json.put("color","#FF0000"); // hex color
                        json.put("device_type",""); // device type
                        json.put("logo", BANNER_URL); // link png/jpg for logo/banner
                        json.put("logo_ratio",1125/396.0); // ratio for logo/banner
                    } catch (JSONException e) {
                        System.out.println("TAG " + e);
                    }
                    result.success(json.toString());
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FLUTTER_ENGINE_ID) {
            if (resultCode == RESULT_OK) {
//                result.success(data.getIntExtra(CountActivity.EXTRA_COUNTER, 0));
            } else {
                result.error("ACTIVITY_FAILURE", "Failed while launching activity", null);
            }
        }
    }
}
