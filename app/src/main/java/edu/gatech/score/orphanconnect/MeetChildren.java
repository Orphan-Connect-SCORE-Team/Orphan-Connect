package edu.gatech.score.orphanconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import edu.gatech.score.orphanconnect.api.Api;
import edu.gatech.score.orphanconnect.api.TestApi;

public class MeetChildren extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean recurring = false;
    final int REQUEST_CODE = 1;
    final String get_token = "API to get token";
    final String send_payment = "API to send payment info";
    String token, amount;
    HashMap<String, String> paramHash;
    final String TEST_SERVER = "http://10.0.2.2:3000";
    String CLIENT_TOKEN = "sandbox_mfgm9rgj_b2rnychf57r462kj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_childern);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView buttonNavigationView = findViewById(R.id.navigation);
        buttonNavigationView.getMenu().setGroupCheckable(0, false, true);
        buttonNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(MeetChildren.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                case R.id.navigation_account:
                    return true;
                case R.id.navigation_donation:
                    startActivity(new Intent(MeetChildren.this, My_Donation.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    return true;
                case R.id.navigation_map:
                    return true;
            }
            return false;
        });

        OrphanViewModel orphanViewModel = ViewModelProviders.of(this).get(OrphanViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final OrphanListAdapter adapter = new OrphanListAdapter(this);
        recyclerView.setAdapter(adapter);
        // Attempt to customize
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // Additional Code not from original guide
        recyclerView.setLayoutManager(layoutManager);

        orphanViewModel.getAllOrphans().observe(this, adapter::setOrphans);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_log_out) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Rewrite this for RecyclerView?
    public void onChildClicked(View view) {
        // inflate the layout of the popup window
        final LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popwindow, null);
        popupView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.popup_anim));
        final Spinner spinner = popupView.findViewById(R.id.donation_frequency);
        spinner.setVisibility(View.INVISIBLE);

        // create the popup window
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, 850, 850, focusable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        Switch recur = popupView.findViewById(R.id.donation_switch);
        recur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recurring) {
                    ((TextView) popupView.findViewById(R.id.donation_type)).setText("Type: One-time");
                    spinner.setVisibility(View.INVISIBLE);
                } else {
                    ((TextView) popupView.findViewById(R.id.donation_type)).setText("Type: Recurring");
                    spinner.setVisibility(View.VISIBLE);

                }
                recurring = !recurring;
            }
        });
        List<String> frequency = new LinkedList<>();
        frequency.add("Every week");
        frequency.add("Every month");
        frequency.add("Every year");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frequency);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner.getSelectedView()).setTextColor(Color.WHITE);
            }
        });

        final View vi = view;
        popupView.findViewById(R.id.donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onDonateClicked(vi);
//                AsyncHttpClient client = new AsyncHttpClient();
//                client.get("https://your-server/client_token", new TextHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, String clientToken) {
//                        CLIENT_TOKEN = clientToken;
//                    }
//                });
                onBraintreeSubmit(v);
                popupWindow.dismiss();
            }
        });

        popupView.findViewById(R.id.donate_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void onBraintreeSubmit(View v) {
        DropInRequest dropInRequest = new DropInRequest().clientToken(CLIENT_TOKEN);
        startActivityForResult(dropInRequest.getIntent(this), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                //update UI and send payment info to server
                postNonceToServer("fake-valid-nonce");
                //startActivity(new Intent(this, MainActivity.class));
            } else if (resultCode == RESULT_CANCELED) {
                //user canceled here
                startActivity(new Intent(this, MainActivity.class));

            } else {
                //error handling, may be an exception
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }
    }

    void postNonceToServer(String nonce) {
        System.out.println("posted?");
        TestApi api = new TestApi();
        api.anotherAccess();
    }

    public void onDonateClicked(View view) {
        // inflate the layout of the popup window
        final LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.confirm, null);
        popupView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.popup_anim));
        // create the popup window
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, 850, 850, focusable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final View vi = view;
        popupView.findViewById(R.id.confirm_ok).setOnClickListener(v -> {
            onConfirmClicked(vi);
            popupWindow.dismiss();
        });
        popupView.findViewById(R.id.confirm_cancel).setOnClickListener(v -> popupWindow.dismiss());
    }

    public void onConfirmClicked(View view) {
        // inflate the layout of the popup window
        final LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.thankyou, null);
        popupView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.popup_anim));
        // create the popup window
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, 850, 850, focusable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        final View vi = view;
        popupView.findViewById(R.id.thankyou_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            return true;
        });
    }
}
