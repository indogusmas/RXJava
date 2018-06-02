package com.example.indo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    Button btnRx;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRx =(Button) findViewById(R.id.buttonrx);
        result = (TextView)findViewById(R.id.tv_textview);
        btnRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    doSomeWork();
            }
        });
    }
    /*
    *Example using Flowable RXjava
     */
    private void doSomeWork(){
        Flowable<Integer> observable = Flowable.just(1,2,3,4,5,6);

        observable.reduce(9, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) throws Exception {
                return t1 + t2;
            }
        }).subscribe(getObserver());
    }

    private SingleObserver<Integer> getObserver(){

        return  new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: "+ d.isDisposed() );
            }

            @Override
            public void onSuccess(Integer integer) {
                Log.e(TAG, "onSuccess: " + integer.toString() );
                result.setText(integer.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
                result.setText(e.getMessage());
            }
        };
    }
}
