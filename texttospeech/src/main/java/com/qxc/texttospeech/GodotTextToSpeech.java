package com.qxc.texttospeech;

import org.godotengine.godot.Godot;
import org.godotengine.godot.plugin.GodotPlugin;

import android.speech.tts.TextToSpeech;

import android.app.Activity;
import android.os.Build;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;



public class GodotTextToSpeech extends GodotPlugin {
    TextToSpeech textToSpeech;


    public GodotTextToSpeech(Godot godot) {
        super(godot);

        // Init TextToSpeech
        textToSpeech = new TextToSpeech(godot, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setPitch(0.6f);
                textToSpeech.setSpeechRate(1.0f);
            }
        });
    }

    public void speak(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
//        super.onDestroy();
        super.onMainDestroy();
    }

    @NonNull
    @Override
    public String getPluginName() {
        return "TTS";
    }

    @NonNull
    @Override
    public List<String> getPluginMethods() {
        return Arrays.asList("speak", "onDestroy");
    }
}
