package com.example.emprendimiento.crimemap;


import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Alejandro Dávila on 10/8/2017.
 */

public class RoboManoArmada extends Delito {
    RoboManoArmada(LatLng loc) {
        super(loc,"Me asaltaron");
    }

    void generarMarcador(LatLng loc)
    {
        Marker robManArm = map.addMarker(new MarkerOptions().position(loc)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
}
