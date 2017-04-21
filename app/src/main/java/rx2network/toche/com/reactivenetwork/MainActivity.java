package rx2network.toche.com.reactivenetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import rx2network.toche.com.reactivenetwork.Retrofit.Response;
import rx2network.toche.com.reactivenetwork.Retrofit.RxNetworkService;

public class MainActivity extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    TextView mTextView;
    RxNetworkService rxNetworkService;
    EditText id,name,age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.txt);
        id = (EditText) findViewById(R.id.id_text);
        name = (EditText) findViewById(R.id.name_text);
        age = (EditText) findViewById(R.id.age_text);


    }

    public void showToken(View view) {
        // แสดง token มาให้ดูหน่อยเสะ
        mTextView.setText(FirebaseInstanceId.getInstance().getToken());
        Log.i("token", FirebaseInstanceId.getInstance().getToken());
    }
    public void subscribe(View view) {
        // สับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        mTextView.setText("Subscribed to Topic news");
    }
    public void unsubscribe(View view) {
        // ยกเลิกสับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
        mTextView.setText("unsubscribed Topic news");
    }

    public void testapi(View view){
        rxNetworkService = new RxNetworkService();
        Call<Response> call = rxNetworkService.getService().tesetApi(
                id.getText().toString(),
                name.getText().toString(),
                age.getText().toString()
        );
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d(TAG,response.body().toString());
                String result ="Response id :="+response.body().getId()+"\n"
                        +"Response name :="+response.body().getName()+"\n"
                        +"Response age :="+response.body().getAge();
                mTextView.setText(result);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG,t.getMessage());
            }
        });
    }
}
