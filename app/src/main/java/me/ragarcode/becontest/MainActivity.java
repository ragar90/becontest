package me.ragarcode.becontest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.gimbal.android.BeaconEventListener;
import com.gimbal.android.BeaconManager;
import com.gimbal.android.BeaconSighting;

import com.gimbal.android.CommunicationManager;
import com.gimbal.android.Gimbal;


public class MainActivity extends ActionBarActivity {
  private BeaconEventListener beaconSightingListener;
  private BeaconManager beaconManager;
  private TextView becontTest;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Gimbal.setApiKey(this.getApplication(), "22d37565-b3fe-457b-bff5-d23893ecaf7b");
    becontTest = (TextView) findViewById(R.id.beconText);
    beaconSightingListener = new BeaconEventListener() {
      @Override
      public void onBeaconSighting(BeaconSighting sighting) {
        becontTest.setText(sighting.toString());
        Log.i("BECON INFO", sighting.toString());
      }
    };
    beaconManager = new BeaconManager();
    beaconManager.addListener(beaconSightingListener);
    beaconManager.startListening();
    CommunicationManager.getInstance().startReceivingCommunications();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
