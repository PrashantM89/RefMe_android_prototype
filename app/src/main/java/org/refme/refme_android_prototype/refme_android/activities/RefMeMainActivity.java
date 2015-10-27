package org.refme.refme_android_prototype.refme_android.activities;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.acra.annotation.ReportsCrashes;
import org.refme.refme_android_prototype.R;

import org.refme.refme_android_prototype.librefme.model.NavigationItems;
import org.refme.refme_android_prototype.librefme.model.ProfileItems;
import org.refme.refme_android_prototype.librefme.resolver.ScriptResolver;
import org.refme.refme_android_prototype.librefme.utils.UrlConstants;
import org.refme.refme_android_prototype.refme_android.Events.ProfileEvent;
import org.refme.refme_android_prototype.refme_android.RefMeApp;
import org.refme.refme_android_prototype.refme_android.adapters.DrawerListAdapter;
import org.refme.refme_android_prototype.refme_android.adapters.TabsPagerAdapter;
import org.refme.refme_android_prototype.refme_android.services.InitService;
import org.refme.refme_android_prototype.refme_android.utils.ImageUtils;
import org.refme.refme_android_prototype.refme_android.utils.RefMeRunnable;
import org.refme.refme_android_prototype.refme_android.utils.ThreadManager;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

@ReportsCrashes(formKey = "",formUri = "http://mandrillapp.com/api/1.0/messages/send.json")
public class RefMeMainActivity extends ActionBarActivity implements ActionBar.TabListener {

    private static final String TAG = RefMeMainActivity.class.getSimpleName();

    private ReportsCrashes mReportsCrashes;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitle;

    private CharSequence mDrawerTitle;

    private ListView mDrawerList;

    private ListView mProfileList;

    private ImageView avatar=null;

    private TextView name= null;

    private EventBus bus;

    private TextView profileName;

    private TextView profileEdit;

    private ScriptResolver scriptResolver;

    public Intent profileEditIntent;

    private ProfileEvent obj = null;

    private ViewPager mViewPager;

    private TabsPagerAdapter tabsPagerAdapter;

    private ActionBar mActionBar;

    private String tabs[]={"Referals","Refer"};

    ArrayList<NavigationItems> mNavItems = new ArrayList<NavigationItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refme_main_activity);

        bus = EventBus.getDefault();
        bus.register(this);

        final RefMeApp refMeApp = new RefMeApp(this.getApplicationContext());

        mNavItems.add(new NavigationItems("Home", "See your referals", R.drawable.home_icon));
        mNavItems.add(new NavigationItems("Preferences", "Change your preferences", R.drawable.settings_icon));
        mNavItems.add(new NavigationItems("Help", "Happy to help you", R.drawable.help_icon));

        Intent intent = new Intent(this, InitService.class);
        intent.putExtra("url", UrlConstants.GET_MY_PROFILE);
        startService(intent);



        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(mNavItems);

        ProfileItems item = new ProfileItems();

        avatar =(ImageView)findViewById(R.id.avatar);
        profileName = (TextView) findViewById(R.id.userName);
        profileEdit = (TextView) findViewById(R.id.desc);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(drawerListAdapter);

        mViewPager = (ViewPager)findViewById(R.id.viewpager);

        mActionBar = getSupportActionBar();
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabsPagerAdapter);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDefaultDisplayHomeAsUpEnabled(true);
        mActionBar.setNavigationMode(mActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            mActionBar.addTab(mActionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        scriptResolver = new ScriptResolver();
        scriptResolver.getWebView().loadUrl("file:///android_asset/index.html");

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);
                mActionBar.setShowHideAnimationEnabled(true);
            }
        });

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 if(mNavItems.get(position).getmTitle().equals("Home")) {
                     Intent intent = new Intent(RefMeMainActivity.this,HelpActivity.class);
                     startActivity(intent);
                     overridePendingTransition(R.anim.pull_right,R.anim.pull_left);
                 }
               else if(mNavItems.get(position).getmTitle().equals("Preferences")) {
                    Intent intent = new Intent(RefMeMainActivity.this,HelpActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_right,R.anim.pull_left);
                }
               else if(mNavItems.get(position).getmTitle().equals("Help")) {
                    Intent intent = new Intent(RefMeMainActivity.this,HelpActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_right,R.anim.pull_left);
                }
            }
        });



        profileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileEditIntent = new Intent(v.getContext(),ProfileActivity.class);
                profileEditIntent.putExtra("profileObj",obj);
                startActivity(profileEditIntent);
            }
        });
        RefMeRunnable r = new RefMeRunnable(1) {
            @Override
            public void run() {


            }
        };

        ThreadManager.getInstance().execute(r);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            showResults(query);
        }
    }

    private void showResults(String query) {
        Toast.makeText(RefMeApp.getContext(),"You Searched: "+query,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        int position = tab.getPosition();
        mViewPager.setCurrentItem(position);

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public void onEvent(ProfileEvent event){
        obj = event;
        avatar.setImageBitmap(ImageUtils.base64ToBitmap(event.getImage()));
        profileName.setText(event.getName());


    }



    public static class RefMeMainReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
              boolean internetConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
                Log.d(TAG,"Internet Connectivity :==========="+internetConnectivity);
                if(internetConnectivity){
                    Toast.makeText(context,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(RefMeApp.getContext(),"Back Pressed",Toast.LENGTH_SHORT).show();
        overridePendingTransition(R.anim.pull_right, R.anim.pull_left);
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ref_me_main, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }
}
