package org.refme.refme_android_prototype.refme_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.refme.refme_android_prototype.R;

/**
 * Created by prashant on 26/9/15.
 */
public class ReferFragment extends Fragment {

    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.referer_fragment, container, false);
        text= (TextView) view.findViewById(R.id.text);

        return view;
    }
}
