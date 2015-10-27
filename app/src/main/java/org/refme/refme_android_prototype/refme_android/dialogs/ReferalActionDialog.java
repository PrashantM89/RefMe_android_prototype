package org.refme.refme_android_prototype.refme_android.dialogs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.model.ReferalItems;
import org.refme.refme_android_prototype.refme_android.RefMeApp;

/**
 * Created by prashant on 4/10/15.
 */
public class ReferalActionDialog extends DialogFragment {
    ReferalItems item;

    public ReferalActionDialog(ReferalItems item){
        this.item = item;
    }
    LayoutInflater linf = LayoutInflater.from(RefMeApp.getContext());
    final View inflatedView = linf.inflate(R.layout.referal_list_item, null);

    TextView t1= (TextView)inflatedView.findViewById(R.id.companyName);
    TextView t2=(TextView)inflatedView.findViewById(R.id.designation);
    TextView exp = (TextView)inflatedView.findViewById(R.id.experience);
    TextView renum = (TextView)inflatedView.findViewById(R.id.renumeration);
    TextView loc = (TextView)inflatedView.findViewById(R.id.location);

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      t1.setText(item.getCompanyName());
      t2.setText(item.getDesignation());
      exp.setText(item.getExp());
      renum.setText(item.getRenumeration());
      loc.setText(item.getLocation());
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                //.setIcon()

                  .setView(inflatedView)
                        // Set Dialog Title
                .setTitle("Apply for this Referal")
                        // Positive button
                .setPositiveButton("Refer me for this job", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RefMeApp.getContext(),"Refered",Toast.LENGTH_SHORT).show();

                    }
                })

                        // Negative Button
                .setNegativeButton("Not Worth", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(RefMeApp.getContext(),"Not Refered",Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}
