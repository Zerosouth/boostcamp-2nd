package appjam.contest.thirdminiproject.main.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import appjam.contest.thirdminiproject.R;
import appjam.contest.thirdminiproject.main.application.ApplicationController;
import appjam.contest.thirdminiproject.main.model.Place;
import appjam.contest.thirdminiproject.main.network.NetworkService;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.cancelText) TextView cancelText;
    @BindView(R.id.relativeLayout) RelativeLayout relativeLayout;
    @BindView(R.id.locationText) TextView locationText;
    @BindView(R.id.nextBtn) Button nextBtn;

    NetworkService service;
    static ArrayList<Place> placeList=new ArrayList<Place>();
    static ArrayList<Marker> markerList=new ArrayList<Marker>();


    //Google Map
    GoogleApiClient mGoogleApiClient = null;
    MapFragment mapFragment;
    GoogleMap map;
    Geocoder gc;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    Marker searchMarker;

    //custom marker, custom window
    View customMarkerView;
    View markerWindowView;
    TextView titleText;

    //custom popup
    PopupWindow popup;
    View popupView;
    TextView popup_titleText;
    TextView popup_locationText;
    TextView popup_numText;
    TextView popup_explainText;

    //permission
    final static int SET_MY_LOCATION = 100;
    final static int CONNECTED = 200;
    //activity code
    final static int INFO_ACTIVITY=300;


    //검색한곳
    String title;
    String location;
    String num;
    String explain;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Log.d("Activity","Add");
        ApplicationController.activityList.add(this);

        ButterKnife.bind(this);

        toolbarSetting();
        markerSetting();
        popupSetting();
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


    //////////////////

    private void markerSetting(){
        //Custom Marker Setting
        customMarkerView=LayoutInflater.from(this).inflate(R.layout.custom_marker,  null);
        markerWindowView = getLayoutInflater().inflate(R.layout.custom_marker_window, null);

        titleText=(TextView)markerWindowView.findViewById(R.id.titleText);


    }
    // View를 Bitmap으로 변환
    private Bitmap createDrawableFromView(View view) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
    /////////////
    private void popupSetting(){

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.popup_window, null);
        popup = new PopupWindow(popupView, 1200, WindowManager.LayoutParams.WRAP_CONTENT, true);

        popup_titleText=(TextView)popupView.findViewById(R.id.titleText);
        popup_locationText=(TextView)popupView.findViewById(R.id.locationText);
        popup_numText=(TextView)popupView.findViewById(R.id.numText);
        popup_explainText=(TextView)popupView.findViewById(R.id.explainText);


        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                relativeLayout.setAlpha((float)1.0);
            }
        });
    }

    /////////////

    private void initSetting() {

        service= ApplicationController.getInstance().getNetworkService();

        //googleApiClient  통합 GoogleAPI!
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).
                    addConnectionCallbacks(this).
                    addOnConnectionFailedListener(this).
                    addApi(LocationServices.API).
                    build();
        }
        //Map Setting
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        //Geocoder Setting
        gc = new Geocoder(this, Locale.KOREAN);


        //다음 Button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nextBtn.getText().toString().equals("다음")){
                    locationText.setText("추가 되었습니다!");
                    nextBtn.setText("새 마커 등록하기");

                    Place place=new Place(title, location, num, explain, searchMarker.getPosition().latitude, searchMarker.getPosition().longitude);
                    //Log.d("latitude,",marker.getPosition().latitude+"");
                    //Log.d("latitude,",marker.getPosition().longitude+"");

                    Call<ArrayList<Place>> save_And_getList=service.save_And_getList(place);
                    save_And_getList.enqueue(new Callback<ArrayList<Place>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Place>> call, Response<ArrayList<Place>> response) {
                            if(response.isSuccessful()){
                                Log.d("retrofit",response.body().size()+"");

                                //데이터 받기
                                placeList=response.body();
                                //여태까지 저장한 곳 마커 설정 및 옵션
                                MarkerOptions options = new MarkerOptions();
                                options.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(customMarkerView)));

                                for(int i=0;i<placeList.size();i++){
                                    //마커 칠하기 및 마커 정보 설정
                                    LatLng latLng=new LatLng(placeList.get(i).getLatitude(), placeList.get(i).getLongitude());
                                    options.position(latLng);
                                    Marker marker = map.addMarker(options);
                                    markerList.add(marker);
                                }

                            }else{
                                Log.d("retrofit","fail 1");
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Place>> call, Throwable t) {
                            Log.d("retrofit","fail 2");

                        }
                    });

                }
                else if(nextBtn.getText().toString().equals("새 마커 등록하기")){

                    searchMarker.remove();
                    for(int i=0;i<markerList.size();i++)
                        markerList.get(i).remove();

                    placeList=new ArrayList<Place>();
                    markerList=new ArrayList<Marker>();

                    Intent intent=new Intent(getApplicationContext(), InfoActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("onMapReady", "fail");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, SET_MY_LOCATION);
        } else {
            Log.d("onMapReady", "success");
            map.setMyLocationEnabled(true);

        }

        //구글맵의 UI 환경 가져오기
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(true); //줌기능 설정



        /////////////마커 클릭했을때
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {

                Place place=searchList(marker);
                titleText.setText(place.getTitle());

                return false;
            }
        });


        //custom marker window
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) { return null; }
            @Override
            public View getInfoContents(Marker marker) {
                return markerWindowView;
            }
        });

        //marker window 클릭했을때
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Place place=searchList(marker);

                popup_titleText.setText(place.getTitle());
                popup_locationText.setText(place.getLocation());
                popup_numText.setText(place.getNum());
                popup_explainText.setText(place.getExplain());

                relativeLayout.setAlpha((float)0.2);
                //클릭시 팝업 윈도우 생성
                LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.mainLayout);
                popup.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
            }
        });


        //입력받은 정보 Setting
        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        location=intent.getStringExtra("location");
        num=intent.getStringExtra("num");
        explain=intent.getStringExtra("explain");

        locationText.setText(location);
        LatLng latLng=searchPlace(location);
        latitude=latLng.latitude;
        longitude=latLng.longitude;

        //입력한 위치 마커 설정 및 옵션
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        options.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(customMarkerView)));
        searchMarker = map.addMarker(options);
        Location location=new Location("");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        updateMap(location);


    }
    public Place searchList(Marker marker){

        if(placeList.size()==0){
            Log.d("searchList","current");
            return new Place(title,location, num, explain,latitude, longitude);

        }

        Log.d("searchList","search");
        double lat=marker.getPosition().latitude;
        double log=marker.getPosition().longitude;

        for(int i=0;i<placeList.size();i++)
            if(placeList.get(i).getLatitude() == lat && placeList.get(i).getLongitude()==log)
                return placeList.get(i);

        return null;
    }

    LatLng searchPlace(String location){

        try {
            List<Address> addr = gc.getFromLocationName(location, 5);

            if(addr!=null){
                return new LatLng(addr.get(0).getLatitude(), addr.get(0).getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //location변수 위치로 맵 바꿈
    public void updateMap(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng Loc = new LatLng(latitude, longitude);

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Loc, 16));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Log.d("GoogleApiClient", "connect");
        // 퍼미션 체크 추가
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CONNECTED);
        }else {
            //마지막 위치
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            //updateMap(mLastLocation);

            //현재 위치 셋팅의 만족 여부 체크
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
            final PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();

                    switch(status.getStatusCode()){
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can
                            // initialize location requests here.
                            Log.d("locationSetting","success");
                            //주기적으로 위치 체크하기
                            mLocationRequest = LocationRequest.create()
                                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                                    .setInterval(10000)
                                    .setFastestInterval(5000)
                                    .setSmallestDisplacement(10);

                            //비동기함수라서
                            // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                            // 이 코드 실행 못해서 함수안에써서 해결
                            requestUpdate();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied, but this can be fixed
                            // by showing the user a dialog.
                            Log.d("locationSetting","resolution required");
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(MapActivity.this, CONNECTED);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way
                            // to fix the settings so we won't show the dialog.
                            Log.d("locationSetting","settings change unavailable");
                            break;

                    }
                }
            });

        }

    }

    private void requestUpdate(){
        Log.d("requestUpdate","success");
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("GoogleApiClient", "suspeneded");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("GoogleApiClient", "failed");
    }

    @Override
    public void onLocationChanged(Location location) {
        //updateMap(location);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("requestCode", requestCode+"");
        Log.d("permissions ", permissions[0]);
        Log.d("permissions length", permissions.length+"");
        Log.d("grantResult", grantResults[0]+"");
        Log.d("grantResult Granted", PackageManager.PERMISSION_GRANTED+"");
        Log.d("grantResult Denied", PackageManager.PERMISSION_DENIED+"");

        if (requestCode == SET_MY_LOCATION) {
            Log.d("requestCode:mylocation","success");
            map.setMyLocationEnabled(true);
        }

        if(requestCode == CONNECTED){

            Log.d("requestcode:CONNECTED","success");
            //마지막 위치
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            //updateMap(mLastLocation);

            //현재 위치 셋팅의 만족 여부 체크
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
            final PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();
                        switch(status.getStatusCode()){
                            case LocationSettingsStatusCodes.SUCCESS:
                                // All location settings are satisfied. The client can
                                // initialize location requests here.
                                Log.d("locationSetting","success");
                                //주기적으로 위치 체크하기
                                mLocationRequest = LocationRequest.create()
                                        .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                                        .setInterval(10000)
                                        .setFastestInterval(5000)
                                        .setSmallestDisplacement(10);

                                //비동기함수라서
                                // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
                                // 이 코드 실행 못해서 함수안에써서 해결
                                requestUpdate();
                                break;
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // Location settings are not satisfied, but this can be fixed
                                // by showing the user a dialog.
                                Log.d("locationSetting","resolution required");
                                try {
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    status.startResolutionForResult(MapActivity.this, CONNECTED);
                                } catch (IntentSender.SendIntentException e) {
                                    // Ignore the error.
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                // Location settings are not satisfied. However, we have no way
                                // to fix the settings so we won't show the dialog.
                                Log.d("locationSetting","settings change unavailable");
                                break;
                        }
                }
            });

        }
    }



}
