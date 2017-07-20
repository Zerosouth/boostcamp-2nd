package appjam.contest.thirdminiproject.main.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

import appjam.contest.thirdminiproject.R;
import appjam.contest.thirdminiproject.main.application.ApplicationController;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jeongdahun on 2017. 7. 17..
 */

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.cancelText) TextView cancelText;
    @BindView(R.id.titleEdit) EditText titleEdit;
    @BindView(R.id.locationEdit) EditText locationEdit;
    @BindView(R.id.numEdit) EditText numEdit;
    @BindView(R.id.stringNumText) TextView stringNumText;
    @BindView(R.id.explainEdit) EditText explainEdit;
    @BindView(R.id.previousBtn) Button previousBtn;
    @BindView(R.id.nextBtn) Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Log.d("Activity","Add");
        ApplicationController.activityList.add(this);

        ButterKnife.bind(this);


        toolbarSetting();
        initSetting();
    }

    /////////////////

    private void toolbarSetting() {

        final Activity activity=this;

        toolbar.setTitle("맛집 등록");
        setSupportActionBar(toolbar);
        //Get the Actionbar here to configure the way it hehaves (커스터마이징 하기 위해서)
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.back); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요

        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ActivityCompat.finishAffinity(activity);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }


        return super.onOptionsItemSelected(item);


    }

    ///////////
    private void initSetting(){


        explainEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                stringNumText.setText("글자수 : "+String.valueOf(s.length())+"/500");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent resultIntent=new Intent();
//                setResult(RESULT_CANCELED,intent);

                //finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("title",titleEdit.getText().toString());
                intent.putExtra("location", locationEdit.getText().toString());
                intent.putExtra("num",numEdit.getText().toString());
                intent.putExtra("explain",explainEdit.getText().toString());
                startActivity(intent);
            }
        });

    }


}
