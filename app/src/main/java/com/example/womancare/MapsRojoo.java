package com.example.womancare;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsRojoo extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_rojoo);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocalizacion();
    }
    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        //--------------------------------------------------------------
        LatLng sydney = new LatLng(21.871185291470297, -102.2546420534595);
        mMap.addMarker(new MarkerOptions().position(sydney).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //--------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney1= new LatLng(21.874938374907575, -102.25857249868831);
        mMap.addMarker(new MarkerOptions().position(sydney1).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney1));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney3 = new LatLng(21.86935275191158, -102.2609328426272);
        mMap.addMarker(new MarkerOptions().position(sydney3).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney3));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney2= new LatLng(21.878472836114113, -102.26771346702158);
        mMap.addMarker(new MarkerOptions().position(sydney2).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney4 = new LatLng(21.877835643935335, -102.28020183213656);
        mMap.addMarker(new MarkerOptions().position(sydney4).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney4));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney5 = new LatLng(21.873773476855256, -102.28067390092053);
        mMap.addMarker(new MarkerOptions().position(sydney5).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney5));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney6 = new LatLng(21.868996225549516, -102.29914181493386);
        mMap.addMarker(new MarkerOptions().position(sydney6).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney6));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney7 = new LatLng(21.913754826233767, -102.31167309538166);
        mMap.addMarker(new MarkerOptions().position(sydney7).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney7));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney8 = new LatLng(21.875368414069438, -102.33261578325332);
        mMap.addMarker(new MarkerOptions().position(sydney8).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney8));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney9 = new LatLng(21.959295406031565, -102.30738156113676);
        mMap.addMarker(new MarkerOptions().position(sydney9).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney9));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney10 = new LatLng(21.871185291470297, -102.2546420534595);
        mMap.addMarker(new MarkerOptions().position(sydney10).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney10));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney11 = new LatLng(221.91761678759495, -102.324891021597);
        mMap.addMarker(new MarkerOptions().position(sydney11).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney11));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney12 = new LatLng(21.905911141141072, -102.33244412214087);
        mMap.addMarker(new MarkerOptions().position(sydney12).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney12));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney13 = new LatLng(21.858600982217855, -102.34480374125953);
        mMap.addMarker(new MarkerOptions().position(sydney13).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney13));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney14 = new LatLng(21.533020516155695, -102.73241513021307);
        mMap.addMarker(new MarkerOptions().position(sydney14).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney14));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney15= new LatLng(21.853661980613417, -102.2914170530778);
        mMap.addMarker(new MarkerOptions().position(sydney15).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney15));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney16= new LatLng(21.869378886468752, -102.25363561092135);
        mMap.addMarker(new MarkerOptions().position(sydney16).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney16));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney17= new LatLng(21.83639835430346, -102.28796788607106);
        mMap.addMarker(new MarkerOptions().position(sydney17).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney17));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney18= new LatLng(21.908722497688547, -102.27612325143984);
        mMap.addMarker(new MarkerOptions().position(sydney18).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney18));
        //------------------------------------------------------------
        //--------------------------------------------------------------
        LatLng sydney19= new LatLng(21.88976883363759, -102.31818028855912);
        mMap.addMarker(new MarkerOptions().position(sydney19).title("zona prohibida").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney19));
        //------------------------------------------------------------
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        LocationManager locationManager = (LocationManager) MapsRojoo.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(miUbicacion).title(" Ubicación  Actual ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(14)
                        .bearing(90)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            @Override
            public void onProviderEnabled(String provider) {
            }
            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }
}