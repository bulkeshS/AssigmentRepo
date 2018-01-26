package com.assignment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.assignment.adapter.ListAdapter;
import com.assignment.api.ApiRequestHelper;
import com.assignment.model.Example;
import com.assignment.util.ConnectionDetector;
import com.assignment.util.ItemOffsetDecoration;
import com.assignment.widget.materialprogress.CustomProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    MyApp app;
    ConnectionDetector connectionDetector;
    Context context;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;
        connectionDetector = new ConnectionDetector(context);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(context, R.dimen.dimen_5);
        app = (MyApp) getApplication();
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(context));
        recyclerview.addItemDecoration(itemDecoration);
        getDetails();

    }

    private void getDetails() {
        if (connectionDetector.isConnectingToInternet()) {
            final CustomProgressDialog pd = new CustomProgressDialog(context);
            pd.setTitle("Loading...");
            pd.show();
            app.getApiRequestHelper().details(new ApiRequestHelper.OnRequestComplete() {
                @Override
                public void onSuccess(Object object) {
                    if (pd != null && pd.isShowing()) {
                        pd.dismiss();
                    }
                    List<Example> address = (List<Example>) object;
                    listAdapter = new ListAdapter(context, address);
                    recyclerview.setAdapter(listAdapter);

                }

                @Override
                public void onFailure(String apiResponse) {
                    Log.d("==Exception", "" + apiResponse);
                    pd.dismiss();
                }
            });
        } else {
            Utils.showShortToast(context, "Please Turn On Internet Connection");
        }
    }
    public void alphaSort(List<Example> address ) {

    }
}
