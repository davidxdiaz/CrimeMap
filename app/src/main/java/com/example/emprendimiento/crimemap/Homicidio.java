package com.example.emprendimiento.crimemap;

import com.google.android.gms.maps.model.Marker;
/**
 * Created by Alejandro Dávila on 10/8/2017.
 */

public abstract class Homicidio extends Delito {

    Homicidio(String comment) {
        super(comment);
    }

    @Override
    Marker generarMarcador() {
        return null;
    }
}
