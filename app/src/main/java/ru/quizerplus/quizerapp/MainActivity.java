package ru.quizerplus.quizerapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.quizerplus.quizerapp.model.Model;
import ru.quizerplus.quizerapp.model.Offer;
import ru.quizerplus.quizerapp.network.NetworkService;

public class MainActivity extends AppCompatActivity implements FragmentClickListener{

    private String login, pass;

    private String name = "", info = "";

    private Realm realm;

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        RealmConfiguration config = Realm.getDefaultConfiguration();
        realm = Realm.getInstance(config);

        FragmentManager fm = getSupportFragmentManager();

        fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new Fragment1();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

    }

    @Override
    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.button_download:
                EditText tv_login = findViewById(R.id.login);
                EditText tv_pass = findViewById(R.id.password);
                login = tv_login.getText().toString();
                pass = tv_pass.getText().toString();
                getData();
                break;
            case R.id.button_show:
                Fragment fragment2 = new Fragment2();
                Bundle bundle = new Bundle();
                bundle.putString(Fragment2.NAME, name);
                bundle.putString(Fragment2.INFO, info);
                fragment2.setArguments(bundle);
                showFragment(fragment2);
                break;
            case R.id.button_clear:
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                showFragment(new Fragment2());
                Toast.makeText(getApplicationContext(),R.string.toast_clear,Toast.LENGTH_LONG).show();
                break;
            case R.id.button_back:
                showFragment(new Fragment1());
                break;
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    private void getData() {
        NetworkService.getInstance()
                .getJSONApi()
                .postData(login, pass)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                        if (response.isSuccessful()) {
                            Model model = response.body();
                            name = model.getName();
                            info = model.getInfo();
                            realm.beginTransaction();
                            realm.deleteAll();
                            for (Offer offer: model.getOffers()) {
                                realm.copyToRealm(offer);
                            }
                            realm.commitTransaction();
                            Toast.makeText(getApplicationContext(),R.string.toast_download_success,Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),R.string.toast_download_fail,Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                        Toast.makeText(getApplicationContext(),R.string.toast_download_error,Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (realm != null) {
            realm.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}
