package com.example.emprendimiento.crimemap;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.*;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import static android.R.attr.phoneNumber;
import static android.provider.Telephony.*;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by David on 10/8/2017.
 */

public class Delito extends AppCompatActivity implements OnMapReadyCallback {


    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    Marker ubicacion;
    GoogleMap map;

    Delito(LatLng loc, String mensaje){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        generarMarcador(loc);
        sendMessage(mensaje);
    }
    //funcion que genera un marcador en el mapa.
    //se emplea en las clases hijas.
    void generarMarcador(LatLng loc) {}

    private boolean checkLocation() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            showAlert();
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    void sendMessage(String men){
        TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Sms.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(mPhoneNumber, null, men, pi, null);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
