package org.refme.refme_android_prototype.refme_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.model.NavigationItems;
import org.refme.refme_android_prototype.refme_android.RefMeApp;

import java.util.ArrayList;

/**
 * Created by prashant on 4/10/15.
 */
public class DrawerListAdapter extends BaseAdapter {

    private ArrayList<NavigationItems> mNavItems;

    public DrawerListAdapter(ArrayList<NavigationItems> navItems) {

        mNavItems = navItems;
    }


    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) RefMeApp.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item, null);
        }else{
            view = convertView;
        }
        TextView titleView = (TextView)view.findViewById(R.id.title);
        TextView subTitleView= (TextView)view.findViewById(R.id.subTitle);
        ImageView iconView = (ImageView)view.findViewById(R.id.icon);

        titleView.setText(mNavItems.get(position).getmTitle());
        subTitleView.setText(mNavItems.get(position).getmSubtitle());
        iconView.setImageResource(mNavItems.get(position).getmImage());

        return view;
    }
}
