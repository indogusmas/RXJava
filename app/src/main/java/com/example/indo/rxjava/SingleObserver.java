package com.example.indo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class SingleObserver extends AppCompatActivity {

    private final String TAG = SingleObserver.class.getSimpleName();

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_observer);
        button = (Button)findViewById(R.id.buttonrx);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSingleObserver();
            }
        });
    }

    /**
     * Example Single Obsever
     */
    private void doSingleObserver(){
        Single.just("Indo")
                .subscribe(getSingleObserver());
    }

    private io.reactivex.SingleObserver<String>getSingleObserver(){
        return new io.reactivex.SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: "+ d.isDisposed() );
            }

            @Override
            public void onSuccess(String s) {
                Log.e(TAG, "onSuccess: "+ s );
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }
        };
    }
}
