package com.example.parkfinder;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "MapActivity";

    double hunterParkLat;
    double hunterParkLon;
    double memoryParkLat;
    double memoryParkLon;
    double middleHeadLat;
    double middleHeadLon;

    LatLng hunterPark = new LatLng(hunterParkLat, hunterParkLon);
    LatLng memoryPark = new LatLng(memoryParkLat, memoryParkLon);
    LatLng middleHead = new LatLng(middleHeadLat, middleHeadLon);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        DatabaseReference hunterParkLatitude = ref.child("Parks").child("Hunter Park").child("Latitude");
        DatabaseReference hunterParkLongitude = ref.child("Parks").child("Hunter Park").child("Longitude");
        DatabaseReference memoryParkLatitude = ref.child("Parks").child("Memory Park").child("Latitude");
        DatabaseReference memoryParkLongitude = ref.child("Parks").child("Memory Park").child("Longitude");
        DatabaseReference middleHeadLatitude = ref.child("Parks").child("Middle Head").child("Latitude");
        DatabaseReference middleHeadLongitude = ref.child("Parks").child("Middle Head").child("Longitude");

        hunterParkLatitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hunterParkLat = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getHunterParkLat:onCancelled", databaseError.toException());
            }
        });

        hunterParkLongitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hunterParkLon = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getHunterParkLon:onCancelled", databaseError.toException());
            }
        });

        memoryParkLatitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memoryParkLat = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMemoryParkLat:onCancelled", databaseError.toException());
            }
        });

        memoryParkLongitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                memoryParkLon = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMemoryParkLon:onCancelled", databaseError.toException());
            }
        });

        middleHeadLatitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                middleHeadLat = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMiddleHeadLat:onCancelled", databaseError.toException());
            }
        });

        middleHeadLongitude.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                middleHeadLon = dataSnapshot.getValue(Double.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMiddleHeadLon:onCancelled", databaseError.toException());
            }
        });

        //KILL ME NOW


    }

    public void hasBbqs(GoogleMap googleMap, DatabaseReference ref) {
        mMap = googleMap;

        DatabaseReference hunterParkBbq = ref.child("Parks").child("Hunter Park").child("BBQ");
        DatabaseReference memoryParkBbq = ref.child("Parks").child("Memory Park").child("BBQ");
        DatabaseReference middleHeadBbq = ref.child("Parks").child("Middle Head").child("BBQ");

        hunterParkBbq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasBbq = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasBbq) {
                    mMap.addMarker(new MarkerOptions().position(hunterPark).title("Hunter Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hunterPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getHunterParkCoords:onCancelled", databaseError.toException());
            }
        });

        memoryParkBbq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasBbq = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasBbq) {
                    mMap.addMarker(new MarkerOptions().position(memoryPark).title("Memory Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(memoryPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMemoryParkCoords:onCancelled", databaseError.toException());
            }
        });

        middleHeadBbq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasBbq = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasBbq) {
                    mMap.addMarker(new MarkerOptions().position(middleHead).title("Middle Head"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(middleHead));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMiddleHeadCoords:onCancelled", databaseError.toException());
            }
        });
    }

    public void hasToilets(GoogleMap googleMap, DatabaseReference ref) {
        mMap = googleMap;

        DatabaseReference hunterParkToilets = ref.child("Parks").child("Hunter Park").child("Toilets");
        DatabaseReference memoryParkToilets = ref.child("Parks").child("Memory Park").child("Toilets");
        DatabaseReference middleHeadToilets = ref.child("Parks").child("Middle Head").child("Toilets");

        hunterParkToilets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasToilets = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasToilets) {
                    mMap.addMarker(new MarkerOptions().position(hunterPark).title("Hunter Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hunterPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getHunterParkCoords:onCancelled", databaseError.toException());
            }
        });

        memoryParkToilets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasToilets = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasToilets) {
                    mMap.addMarker(new MarkerOptions().position(memoryPark).title("Memory Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(memoryPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMemoryParkCoords:onCancelled", databaseError.toException());
            }
        });

        middleHeadToilets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean hasToilets = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (hasToilets) {
                    mMap.addMarker(new MarkerOptions().position(middleHead).title("Middle Head"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(middleHead));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMiddleHeadCoords:onCancelled", databaseError.toException());
            }
        });
    }

    public void isPetFriendly(GoogleMap googleMap, DatabaseReference ref) {
        mMap = googleMap;

        DatabaseReference hunterParkPets = ref.child("Parks").child("Hunter Park").child("Pet Friendly");
        DatabaseReference memoryParkPets = ref.child("Parks").child("Memory Park").child("Pet Friendly");
        DatabaseReference middleHeadPets = ref.child("Parks").child("Middle Head").child("Pet Friendly");

        hunterParkPets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isPetFriendly = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (isPetFriendly) {
                    mMap.addMarker(new MarkerOptions().position(hunterPark).title("Hunter Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hunterPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getHunterParkCoords:onCancelled", databaseError.toException());
            }
        });

        memoryParkPets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isPetFriendly = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (isPetFriendly) {
                    mMap.addMarker(new MarkerOptions().position(memoryPark).title("Memory Park"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(memoryPark));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMemoryParkCoords:onCancelled", databaseError.toException());
            }
        });

        middleHeadPets.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isPetFriendly = Boolean.parseBoolean(dataSnapshot.getValue(String.class));
                if (isPetFriendly) {
                    mMap.addMarker(new MarkerOptions().position(middleHead).title("Middle Head"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(middleHead));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "getMiddleHeadCoords:onCancelled", databaseError.toException());
            }
        });
    }
}
