package com.example.newapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class dlist extends ArrayAdapter<plist>
{
private Activity context;
private List<plist>dlist;

public dlist(Activity context,List<plist>dlist){
    super(context,R.layout.list_layout,dlist);
    this.context=context;
    this.dlist=dlist;
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView NaID=(TextView) listViewItem.findViewById(R.id.NaID);
        TextView PrID=(TextView)listViewItem.findViewById(R.id.PrID);
        plist Plist=dlist.get(position);
     NaID.setText(Plist.getAddress());
     PrID.setText(Plist.getPrice());
     return listViewItem;
    }
}

