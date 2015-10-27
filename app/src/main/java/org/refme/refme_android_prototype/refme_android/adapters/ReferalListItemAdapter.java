package org.refme.refme_android_prototype.refme_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.model.ReferalItems;

import java.util.List;

/**
 * Created by prashant on 27/9/15.
 */
public class ReferalListItemAdapter extends ArrayAdapter {

    private Context context;
    private List<ReferalItems> list;

    public ReferalListItemAdapter(Context context, List<ReferalItems> objects) {
        super(context, R.layout.referal_list_item, objects);
        this.context = context;
        this.list = objects;
    }

    public View getView(int position,View convertView,ViewGroup parent){

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.referal_list_item,parent,false);
        }
        TextView companyName = (TextView)convertView.findViewById(R.id.companyName);
        TextView experience = (TextView)convertView.findViewById(R.id.experience);
        TextView location = (TextView)convertView.findViewById(R.id.location);
        TextView designation = (TextView)convertView.findViewById(R.id.designation);
        TextView renumeration = (TextView)convertView.findViewById(R.id.renumeration);

        ReferalItems item = list.get(position);

        companyName.setText(item.getCompanyName());
        experience.setText(item.getExp());
        location.setText(item.getLocation());
        designation.setText(item.getDesignation());
        renumeration.setText(item.getRenumeration());

        return convertView;
    }


}
