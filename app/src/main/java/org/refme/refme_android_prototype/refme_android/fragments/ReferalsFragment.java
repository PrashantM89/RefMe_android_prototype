package org.refme.refme_android_prototype.refme_android.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.refme.refme_android_prototype.R;
import org.refme.refme_android_prototype.librefme.model.ReferalItems;
import org.refme.refme_android_prototype.librefme.utils.UrlConstants;
import org.refme.refme_android_prototype.refme_android.RefMeApp;
import org.refme.refme_android_prototype.refme_android.adapters.ReferalListItemAdapter;
import org.refme.refme_android_prototype.refme_android.dialogs.ReferalActionDialog;
import org.refme.refme_android_prototype.refme_android.services.ReferalDownloadService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prashant on 26/9/15.
 */
public class ReferalsFragment extends Fragment {

    private RequestQueue requestQueue;

    ReferalListItemAdapter adapter;

    List<ReferalItems> list1;

    private static final String TAG = ReferalsFragment.class.getSimpleName();

    ProgressDialog progressDialog;

    ListView listView;

    private FragmentManager fragmentManager;

    private SwipeRefreshLayout swipeRefreshLayout;

    ActionBar mActionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.referals_fragment,null);
        listView = (ListView)view.findViewById(R.id.list);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              //  getDataOnline(UrlConstants.GET_MY_REFERALS);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // fragmentManager;
        mActionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                       // swipeRefreshLayout.setRefreshing(true);
                                        //getDataOnline(UrlConstants.GET_MY_REFERALS);
                                    }
                                 }
                                );
        list1 = new ArrayList<ReferalItems>();

       ReferalItems item = new ReferalItems();
        item.setDesignation("Software Engineer");
        item.setExp("2 yrs");
        item.setCompanyName("Infosys Ltd");
        item.setRenumeration("4 Lpa");
        item.setLocation("Pune");
        list1.add(item);
        ReferalItems item2 = new ReferalItems();
        item2.setDesignation("Sr. Software Engineer");
        item2.setExp("3 yrs");
        item2.setCompanyName("Wipro Pvt Ltd");
        item2.setRenumeration("6 Lpa");
        item2.setLocation("Mumbai");
        list1.add(item2);


        requestQueue = Volley.newRequestQueue(RefMeApp.getContext());
        Intent intent = new Intent(Intent.ACTION_SYNC, null, RefMeApp.getContext(), ReferalDownloadService.class);
        getActivity().startService(intent);
        //getDataOnline(UrlConstants.GET_MY_REFERALS);
        adapter = new ReferalListItemAdapter(RefMeApp.getContext(),list1);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    mActionBar.hide();
                    Toast.makeText(RefMeApp.getContext(),"scrolling",Toast.LENGTH_SHORT).show();
                }
                if(mLastFirstVisibleItem>firstVisibleItem)
                {
                    mActionBar.show();
                    Toast.makeText(RefMeApp.getContext(),"scrolling",Toast.LENGTH_SHORT).show();
                }
                mLastFirstVisibleItem=firstVisibleItem;

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReferalItems selectedItem = (ReferalItems)adapter.getItem(position);
                ReferalActionDialog referalActionDialog = new ReferalActionDialog(selectedItem);

                referalActionDialog.show(getFragmentManager(),"Referal Dialog Fragment");

                //Toast.makeText(RefMeApp.getContext(),"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataOnline(String url) {

        Log.d(TAG,"URL:"+url);
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                hideProgress();
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object = response.getJSONObject(i);

                        ReferalItems item = new ReferalItems();
                        item.setCompanyName(object.getString("companyName"));
                        item.setLocation("pune");
                        item.setDesignation(object.getString("jobTitle"));
                        item.setRenumeration("4 lpa");
                        item.setExp(object.getString("experience"));
                        list1.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

        },new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                hideProgress();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(RefMeApp.getContext(),"Cannot connect to server. Check your internet connection.",Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Error:"+volleyError.getMessage());
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(6000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(jsonArrayRequest);
    }

    private void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
