package com.example.vdovin.bankproject.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vdovin.bankproject.R;
import com.example.vdovin.bankproject.extra.LocationProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by vdovin on 6/20/16.
 */
public class MapsFragment extends Fragment implements LocationProvider.LocationCallback{

    SupportMapFragment mapFragment;
    GoogleMap mMap;
    FragmentManager fm;

    private LocationProvider mLocationProvider;

    private List<String> bankAddressList = new ArrayList<String>();
    private List<Location> locationList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_layout, container, false);

        bankAddressList.add("Bulevardul Grigore Vieru 1 , sector Centru, Chisinau, MD-2005");
        bankAddressList.add("str. A. Puskin 60/2 , sector Centru, Chisinau, MD-2005");
        bankAddressList.add("str. Independentei 1/1 , sector Botanica, Chisinau, MD-2043");
        bankAddressList.add("str. Tighina 23/3 , sector Centru, Chisinau, MD-2001");
        bankAddressList.add("str. Ismail 33");
        bankAddressList.add("bd. Stefan cel Mare si Sfint 171/1");
        bankAddressList.add("str.Puschin 26");
        bankAddressList.add("bd. Stefan cel Mare si Sfint 81A");
        bankAddressList.add("str. Constantin Tanase 9");
        bankAddressList.add("bd. Stefan cel Mare si Sfint 65");
        bankAddressList.add("str. 31 August 1989 141");

        Location bnm = new Location("National Bank of Moldova");
        bnm.setLatitude(47.029195);
        bnm.setLongitude(28.838029);
        Location bcr = new Location("Commercial Bank of Romania");
        bcr.setLatitude(47.029831);
        bcr.setLongitude(28.846530);
        Location comert = new Location("ComertBank");
        comert.setLatitude(46.986003);
        comert.setLongitude(28.845112);
        Location energ = new Location("EnergBank");
        energ.setLatitude(47.013509);
        energ.setLongitude(28.837767);
        Location euroCredit = new Location("EuroCreditBank");
        euroCredit.setLatitude(47.015717);
        euroCredit.setLongitude(28.844468);
        Location exim = new Location("EximBank");
        exim.setLatitude(47.036284);
        exim.setLongitude(28.813723);
        Location fincom = new Location("FincomBank");
        fincom.setLatitude(47.023249);
        fincom.setLongitude(28.833915);
        Location mobias = new Location("MobiasBank");
        mobias.setLatitude(47.021303);
        mobias.setLongitude(28.834800);
        Location moldincon = new Location("MoldinconBank");
        moldincon.setLatitude(47.017886);
        moldincon.setLongitude(28.837672);
        Location agroind = new Location("MAIB");
        agroind.setLatitude(47.028394);
        agroind.setLongitude(28.837573);
        Location proCredit = new Location("ProcreditBank");
        proCredit.setLatitude(47.015913);
        proCredit.setLongitude(28.843770);
        Location victoria = new Location("VictoriaBank");
        victoria.setLatitude(47.028028);
        victoria.setLongitude(28.820865);

        locationList.add(bnm);
        locationList.add(bcr);
        locationList.add(comert);
        locationList.add(energ);
        locationList.add(euroCredit);
        locationList.add(exim);
        locationList.add(fincom);
        locationList.add(mobias);
        locationList.add(moldincon);
        locationList.add(agroind);
        locationList.add(proCredit);
        locationList.add(victoria);

        mLocationProvider = new LocationProvider(getActivity(), this);

        /*fm = getChildFragmentManager();

        mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, mapFragment).commit();
        }*/
        setUpMapIfNeeded();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mLocationProvider.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationProvider.disconnect();
    }
    @Override
    public void handleNewLocation(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        mMap.addMarker(options).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11.5f));
        nearestBank(location);
        /* setUpBanks(47.029195, 28.838029);//bnm(Grigore Vieru 1)
        setUpBanks(47.029831, 28.846530);//bcr(Puskin 60/5)
        setUpBanks(46.986003, 28.845112);//comert(Independentei 1/1)
        setUpBanks(47.013509, 28.837767);//energ(Tighina 23/3)
        setUpBanks(47.015717, 28.844468);//eurocredit(Ismail 33)
        setUpBanks(47.036284, 28.813723);//exim(Stefan Cel Mare si Sfint 171/1)
        setUpBanks(47.023249, 28.833915);//fincom(Puskin 26)
        setUpBanks(47.021303, 28.834800);//mobias(Stefan Cel Mare si Sfint 81A)
        setUpBanks(47.017886, 28.837672);//moldindcon(Armeneasca 38)
        setUpBanks(47.028394, 28.837573);//moldova-agroind(Constantin Tanase 9)
        setUpBanks(47.015913, 28.843770);//procredit(Stefan Cel Mare si Sfint 65)
        setUpBanks(47.028028, 28.820865);//victoria(31 August 1989 141)
        */
        /*
        for(String bankAddress : bankAddressList){
            try {
                setUpBanks(bankAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/


    }

    private void nearestBank(Location myLocation){
        int position = 0;
        float minDistance = myLocation.distanceTo(locationList.get(0));
        for (int i = 1; i < locationList.size(); i++) {
            float tmpDistance = myLocation.distanceTo(locationList.get(i));
            if(minDistance > tmpDistance) {
                minDistance = tmpDistance;
                position = i;
            }
        }

        mMap.addMarker(new MarkerOptions().position(new LatLng(locationList.get(position).getLatitude(),
                locationList.get(position).getLongitude())).title(locationList.get(position).getProvider()))
                .setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

        for (int i = 0; i < locationList.size(); i++) {

            if(i != position){
                mMap.addMarker(new MarkerOptions().position(new LatLng(locationList.get(i).getLatitude(),
                        locationList.get(i).getLongitude())).title(locationList.get(i).getProvider()));
            }

        }


    }

    private void setUpBanks(double latitude, double longitude) {
        /*Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocationName(bankAddress, 1);
        if (addresses.size() > 0) {
            Address address = addresses.get(0);
            double longitude = address.getLongitude();
            double latitude = address.getLatitude();*/

            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {


            fm = getChildFragmentManager();

            mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.map, mapFragment).commit();
            }

            mMap = mapFragment.getMap();
        }
    }


}

