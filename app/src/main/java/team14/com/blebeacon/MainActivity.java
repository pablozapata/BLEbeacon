package team14.com.blebeacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements StartBeacon.StartBeaconCallback {

    private static final String TAG =  MainActivity.class.getName();

    private BluetoothAdapter adapter;
    private StartBeacon startBeacon;


    @Override
    public void onDiscoveredBeacon(int ID, float voltage, float temperature, float d) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothManager manager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        adapter = manager.getAdapter();
        if (adapter == null){
            Log.e(TAG, "Bluetooth Adapter not available");
            Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_SHORT).show();

            return;
        }
        if (!adapter.isEnabled()) {
            Log.d(TAG, "onCreate: Bluetooth is not turned on, turning on bluetooth");
            Toast.makeText(getApplicationContext(),"Bluetooth is not turned on, turning on bluetooth",Toast.LENGTH_SHORT).show();
            adapter.enable();
        }

        startBeacon= new StartBeacon(this, adapter, this);
        startBeacon.startService();

    }
}
