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
import org.refme.refme_android_prototype.librefme.model.ProfileItems;
import org.refme.refme_android_prototype.refme_android.RefMeApp;
import org.refme.refme_android_prototype.refme_android.utils.ImageUtils;

import java.sql.Ref;
import java.util.ArrayList;

/**
 * Created by prashant on 11/10/15.
 */
public class ProfileItemAdapter extends BaseAdapter {


    Context context = RefMeApp.getContext();

    private ProfileItems items;

    public ProfileItemAdapter(ProfileItems items) {

        this.items = items;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return items;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.referer_profile_item,null);
        }else{
            view = convertView;
        }
        ImageView mProfileImg = (ImageView)view.findViewById(R.id.avatar);
        TextView mProfileName = (TextView)view.findViewById(R.id.userName);

       // mProfileImg.setImageBitmap(ImageUtils.base64ToBitmap(items.getImageUrl()));
        mProfileName.setText(items.getName());

        return view;
    }
}
