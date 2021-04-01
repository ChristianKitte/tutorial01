package de.ckitte.tutorial01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.NotActiveException;
import java.io.PrintStream;


public class myReplaceFragment extends Fragment {
    public myReplaceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_replace, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0);
        EditText txtEingabe = view.findViewById(R.id.txtSaveValue);
        String x= pref.getString("MyPref",null);

        txtEingabe.setText(pref.getString("lastText", "not set"));

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtEingabe = view.findViewById(R.id.txtSaveValue);
                String eingabeText = txtEingabe.getText().toString();

                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("lastText", eingabeText).commit();
            }
        });
    }
}
