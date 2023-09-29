package com.example.kakao_sound;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import android.content.pm.PackageManager;
import android.util.Log;
import android.Manifest;

import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.StringTokenizer;

import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SubActivity extends Activity {
    //OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sub_activity);
        ImageButton mic_sound = findViewById(R.id.mic_sound);

        // 권한 설정
        SpeechRecognizerManager.getInstance().initializeLibrary(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 5);
            Toast.makeText(SubActivity.this, "권한 불가 시 음성 인식 기능을 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

        mic_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSound();
            }
        });

    }

    //음성인식
    public void inputSound() {
        String serviceType = SpeechRecognizerClient.SERVICE_TYPE_WEB;
        final SpeechRecognizerClient.Builder builder = new SpeechRecognizerClient.Builder().setServiceType(serviceType);
        SpeechRecognizerClient client = builder.build();
        client.setSpeechRecognizeListener(new SpeechRecognizeListener() {
            @Override
            public void onReady() {
                Log.d("SubActivity", "모든 준비 완료");
            }

            @Override
            public void onBeginningOfSpeech() {
                Log.d("SubActivity", "말하기 시작 했습니다.");
            }

            @Override
            public void onEndOfSpeech() {
                Log.d("SubActivity", "말하기가 끝났습니다.");
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
            }

            @Override
            public void onPartialResult(String partialResult) {
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> texts = results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS);
                List<String> result_sound = new ArrayList<>(texts);
                String elements = result_sound.get(0);

                TextView textView = findViewById(R.id.text);
                textView.setText(elements);
                StringTokenizer stringTokenizer = new StringTokenizer(elements);
                int numtoken = stringTokenizer.countTokens();
                System.out.println("numtoken : "+numtoken);
                if ((elements.contains("년")) && (elements.contains("월")) &&
                        elements.contains("일") && elements.contains("개"))
                    if (numtoken == 5 || numtoken == 10 || numtoken == 15 || numtoken == 20) {
                        Intent intent = new Intent(SubActivity.this, alert_service.class);
                        intent.putExtra("elements", elements);
                        startService(intent);
                    } else
                        toast("년,월,일 또는 개수가 포함되지 않았습니다.\n " + "음성 인식 버튼을 다시 눌러주세요");
            }

            @Override
            public void onAudioLevel(float audioLevel) {
            }

            @Override
            public void onFinished() {

            }
        });

        client.startRecording(true);

        Toast.makeText(this, "음성인식을 시작합니다.", Toast.LENGTH_SHORT).show();
    }

    //toast 메시지 출력
    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}

