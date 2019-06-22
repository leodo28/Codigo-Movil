package com.example.tecsup.nfc_activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Listener {
    Button escribir,leer;
    EditText texto;
    boolean isWrite = false;
    NFCWriteFragment nfcWriteFragment;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escribir = findViewById(R.id.escribir);
        leer = findViewById(R.id.leer);
        texto = findViewById(R.id.texto);

        escribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWriteFragment();
            }
        });
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(this,getClass())
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);
        if(nfcAdapter!=null)
            nfcAdapter.enableForegroundDispatch(this,pendingIntent,nfcIntentFilter,null);
    }

    @Override
    public void onDialogDisplayed() {

    }

    @Override
    public void onDialogDismissed() {

    }

    private void showWriteFragment(){
        isWrite = true;
        nfcWriteFragment = (NFCWriteFragment)
                getSupportFragmentManager().findFragmentByTag(NFCWriteFragment.TAG);
        if(nfcWriteFragment==null){
            nfcWriteFragment = NFCWriteFragment.newInstance();
        }
        nfcWriteFragment.show(getSupportFragmentManager(),NFCWriteFragment.TAG);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.d("NFC","onNewIntent: "+intent.getAction());

        if (tag!= null){
            Toast.makeText(this,"Se detecto tarjeta",Toast.LENGTH_SHORT).show();
            Ndef ndef = Ndef.get(tag);
            if(isWrite){
                String messageToWrite = texto.getText().toString();
                nfcWriteFragment = (NFCWriteFragment) getSupportFragmentManager()
                        .findFragmentByTag(NFCWriteFragment.TAG);
                nfcWriteFragment.onNfcDetected(ndef,messageToWrite);
            }
            else{
                try {
                    ndef.connect();
                    NdefMessage ndefMessage = ndef.getNdefMessage();
                    String message = new String (ndefMessage.getRecords()[0].getPayload());
                    Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
                    ndef.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        }
    }
}
