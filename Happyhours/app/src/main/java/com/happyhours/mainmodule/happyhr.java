//https://console.developers.google.com/apis/credentials?project=earnest-coder-171805

//code took from http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/

// https://sacramento.downtowngrid.com/eats-drinks/happy-hour-2/
package com.happyhours.mainmodule;

import android.*;
import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Map;

public class happyhr extends ListActivity implements LocationListener {

    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    ListView list;
    Double lat;
    Double lng;
    String[] itemname = {
            "58 Degrees & Holding Co.",
            "Adamo's Restaurant",
            "Aura Korean and Japanese Restaurant",
            "Azul Mexican Food & Tequila Bar",
            "Biba Restaurant",
            "Bombay Bar & Grill",
            "Broderick Roadhouse",
            "Capitol Garage",
            "Cornerstone Restaurant",
            "Dive Bar",
            "Downtown & Vine",
            "Ernesto's Mexican Food",
            "Espanol Italian Restaurant"
    };

    String[] address = {
            "1217 18th Street Sacramento, CA 95811",
            "2107 P Street Sacramento, CA 95816",
            "1401 G Street Sacramento, CA 95814",
            "1050 20th St Sacramento, CA 95811",
            "2801 Capitol Ave. Sacramento, CA 95816",
            "1315 21st Street Sacramento, CA 95811",
            "319 Sixth Street West Sacramento, CA 95605",
            "1500 K Street Sacramento, CA 95814",
            "2326 J Street Sacramento, CA 95816",
            "1016 K Street Sacramento, CA 95814",
            "1200 K Street Sacramento, CA 95814",
            "1901 16th Street Sacramento, CA 95814",
            "5723 Folsom Blvd. Sacramento, CA 95819"
    };

    Integer[] imgid = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happyhr);
       /* //1
        latituteField = (TextView) findViewById(R.id.TextView02);
        longitudeField = (TextView) findViewById(R.id.TextView04);*/

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            //2
            //latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }


        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid, address);
        list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Selcteditem = itemname[+position];
                //Toast.makeText(getApplicationContext(), Selcteditem, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), lat.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), lng.toString(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(happyhr.this, MapsActivity.class);
                myIntent.putExtra("lat", lat.toString()); //Optional parameters
                myIntent.putExtra("long", lng.toString());
                happyhr.this.startActivity(myIntent);

            }
        });

        // Add a header to the ListView
        //String str=(TextView)findViewById(android.R.id);
       // LayoutInflater inflater = getLayoutInflater();
       // ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, list, false);
       // list.addHeaderView(header);
    }

    /*
        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            // ListView Clicked item index
            int itemPosition     = position;
            // ListView Clicked item value
            String  itemValue    = (String) l.getItemAtPosition(position);
            Log.d(itemValue.toString(),"I am printing the item value");
            //content.setText("Click : \n  Position :"+itemPosition+"  \n  ListItem : " +itemValue);

        }*/
    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();
        //3
       // latituteField.setText(String.valueOf(lat));
       // longitudeField.setText(String.valueOf(lng));
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      /*  client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "happyhr Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://friendlychat.firebase.google.com/message"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.happyhours.mainmodule/http/friendlychat.firebase.google.com/message")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     /*   Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "happyhr Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://friendlychat.firebase.google.com/message"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.happyhours.mainmodule/http/friendlychat.firebase.google.com/message")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();*/
    }
}
