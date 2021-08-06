package mb.ganesh.swiggyintro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class IntroActivity extends AppCompatActivity {


    ViewPager viewPager;
    Adapter adapter;
    MaterialButton startBtn;
    TabLayout tabIndicator;
    int position = 0;
    List<Model> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()){
            startActivity(new Intent(IntroActivity.this , HomeActivity.class));
            finish();
        }

        setContentView(R.layout.activity_intro);


        tabIndicator = findViewById(R.id.tabIndicator);
        startBtn = findViewById(R.id.startBtn);

        mList = new ArrayList<>();
        mList.add(new Model("Video Classes", R.drawable.video));
        mList.add(new Model("User Friendly UI", R.drawable.friend));
        mList.add(new Model("Quiz for every topics", R.drawable.exam));
        mList.add(new Model("2Marks and 5Marks", R.drawable.marks));
        mList.add(new Model("Offline Functionality", R.drawable.offline));

        viewPager = findViewById(R.id.viewPagerId);
        adapter = new Adapter(this, mList);
        viewPager.setAdapter(adapter);
        tabIndicator.setupWithViewPager(viewPager);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, HomeActivity.class));
                saveData();
                finish();
            }
        });

        runThread();

    }

    private void runThread() {

        new Thread() {
            public void run() {
                while (position < mList.size()) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                viewPager.setCurrentItem(position);
                                position++;
                                if (position == mList.size()){
                                    position=0;
                                    return;
                                }
                            }
                        });
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }



    private boolean restorePrefData() {
        SharedPreferences preferences = getSharedPreferences("myPref", MODE_PRIVATE);
        return preferences.getBoolean("isOpened", false);
    }


    public void saveData() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isOpened", true);
        editor.apply();

    }
}