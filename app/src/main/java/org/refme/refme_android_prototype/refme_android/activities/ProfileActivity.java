package org.refme.refme_android_prototype.refme_android.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.model.ProfileItems;
import org.refme.refme_android_prototype.refme_android.Events.ProfileEvent;
import org.refme.refme_android_prototype.refme_android.RefMeApp;
import org.refme.refme_android_prototype.refme_android.utils.ImageUtils;

import de.greenrobot.event.EventBus;

public class ProfileActivity extends ActionBarActivity {

    private ImageView image;
    private TextView name;
    private TextView company;
    private TextView experience;
    private TextView designation;
    private TextView email;
    private TextView hrmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = (ImageView)findViewById(R.id.imageView);
        name = (TextView)findViewById(R.id.my_name);
        company = (TextView)findViewById(R.id.my_compamy);
        experience = (TextView)findViewById(R.id.my_experience);
        email = (TextView)findViewById(R.id.my_email);
        designation = (TextView)findViewById(R.id.my_designation);
        hrmail = (TextView)findViewById(R.id.my_hremail);

        ProfileEvent model = (ProfileEvent) getIntent().getSerializableExtra("profileObj");

       // image.setImageBitmap(ImageUtils.base64ToBitmap(model.getImage()));
        name.setText(model.getName());
        company.setText(model.getCompany());
        experience.setText(String.valueOf(model.getExperience()));
        email.setText(model.getEmail());
        hrmail.setText(model.getEmail());
        designation.setText(model.getDesignation());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
