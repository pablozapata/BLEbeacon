package team14.com.blebeacon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.util.Log;

/**
 * Created by pablo on 5/06/17.
 */

public class StartBeacon extends BluetoothGattCallback implements BLEScanner.BLEScannerCallback {

    private static final String TAG = StartBeacon.class.getName();

    public interface StartBeaconCallback{
        void onDiscoveredBeacon(int ID, float voltage, float temperature, float d);

    }

    private Context mContext;
    private BluetoothAdapter mAdapter;
    private StartBeaconCallback mCallback;
    private BLEScanner bleScanner;
    private boolean mRunning;
    private boolean mDeviceConnected;
    private boolean mBusy;
    private BluetoothGatt mGatt;

    public StartBeacon(Context context, BluetoothAdapter adapter, StartBeaconCallback callback){
        this.mContext= context;
        this.mAdapter = adapter;
        this.mCallback= callback;
        this.bleScanner= new BLEScanner(adapter, this);
        mRunning= false;
        mDeviceConnected= false;
        mBusy=false;

    }

    public void startService(){
        if (!mRunning){
            bleScanner.startScan();
        } else{
            Log.d(TAG, "startService: System alredy runnning");
        }
    }

    @Override
    public void onDeviceFound(ScanResult scanResult) {
        Log.d(TAG, "result of the scanning" +scanResult);
    }

    @Override
    public void onScanFailed(BLEScanner.ScannerFailureReason reason) {

    }

    @Override
    public void onScanCompleted() {

    }
}
