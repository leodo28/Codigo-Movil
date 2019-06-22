package com.example.tecsup.nfc_activity;


import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.nio.charset.Charset;


/**
 * A simple {@link Fragment} subclass.
 */
public class NFCWriteFragment extends DialogFragment {

    public static final String TAG = NFCWriteFragment.class.getSimpleName();

    public static NFCWriteFragment newInstance (){
        return new NFCWriteFragment();
    }

    private TextView mTvMessage;
    private Listener listener;


    public NFCWriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_nfcwrite, container, false);
        mTvMessage = v.findViewById(R.id.mensaje);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (MainActivity)context;
        listener.onDialogDisplayed();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.onDialogDismissed();
    }

    public void onNfcDetected(Ndef ndef, String messageToWrite){
        writeToNfc(ndef,messageToWrite);
    }

    private void writeToNfc (Ndef ndef, String message){
        mTvMessage.setText("Escribiendo Etiqueta");
        if(ndef!=null){
            try{
                ndef.connect();
                NdefRecord mimeRecord=NdefRecord.createMime("text/plain",
                        message.getBytes(Charset.forName("US-ASCII")));
                ndef.writeNdefMessage(new NdefMessage(mimeRecord));
                ndef.close();
                mTvMessage.setText("Correcto");
            }
            catch(IOException|FormatException e){
                e.printStackTrace();
                mTvMessage.setText("Fallo");
            }
        }
    }
}
