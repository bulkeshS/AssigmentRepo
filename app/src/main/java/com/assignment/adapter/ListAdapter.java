package com.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.assignment.R;
import com.assignment.model.Address;
import com.assignment.model.Company;
import com.assignment.model.Example;
import com.assignment.model.Geo;
import com.assignment.widget.CustomTextViewRegular;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bulkesh on 1/26/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerViewHolder> {
    Context context;
    List<Example> address;

    public ListAdapter(Context context, List<Example> address) {
        this.context = context;
        this.address = address;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listing, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final Example example= address.get(position);
        final Company company=example.getCompany();
        final Address add=example.getAddress();
        final Geo geo=add.getGeo();
        holder.tvTitle.setText(example.getName());
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ArrayList<String> wordList = new ArrayList<String>();
                wordList.add(example.getName().toLowerCase());
                wordList.add(example.getUsername().toLowerCase());
                wordList.add(example.getPhone());
                wordList.add(example.getEmail().toLowerCase());
                wordList.add(add.getStreet().toLowerCase());
                wordList.add(add.getCity().toLowerCase());
                wordList.add(add.getZipcode());
                wordList.add(geo.getLat());
                wordList.add(geo.getLng());
                wordList.add(company.getBs().toLowerCase());
                wordList.add(company.getCatchPhrase().toLowerCase());
                wordList.add(company.getName().toLowerCase());
              // String range[] = new String[]{example.getName(),example.getPhone(),example.getUsername(),example.getEmail(),example.getWebsite(),company.getName(),company.getBs(),company.getCatchPhrase(),
                //add.getStreet(),add.getCity(),add.getZipcode(),add.getSuite(),geo.getLat(),geo.getLng()};
                String[] alphaList = new String[wordList.size()];
                int count = 0;
                while(count < wordList.size()) {
                    alphaList[count] = wordList.get(count);
                    count++;
                }
                int shortestStringIndex;
                for(int j = 0; j < alphaList.length - 1; j++) {
                    shortestStringIndex = j;
                    for(int i = j + 1; i < alphaList.length; i++) {
                        if(alphaList[i].trim().compareTo(alphaList[shortestStringIndex].trim()) < 0) {
                            shortestStringIndex = i;
                        }
                    }
                    if(shortestStringIndex != j) {
                        String temp = alphaList[j];
                        alphaList[j] = alphaList[shortestStringIndex];
                        alphaList[shortestStringIndex]= temp;
                    }
                }
                count = 0;
                while(count < alphaList.length) {
                    System.out.println(alphaList[count++]);
                }
                new MaterialDialog.Builder(context)
                        .title("Detail of a Person")
                        .items(alphaList)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                Log.d("==", "" + "do nothing");

                            }
                        })
                        .show();
            }



        });
    }

    @Override
    public int getItemCount() {
        return address.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.tv_title)
        CustomTextViewRegular tvTitle;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public View getView() {
            return itemView;
        }
    }
}
