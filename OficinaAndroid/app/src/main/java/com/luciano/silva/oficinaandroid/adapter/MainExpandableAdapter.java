package com.luciano.silva.oficinaandroid.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.luciano.silva.oficinaandroid.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Adapter para a tela Main que mostra uma ExpandableListView
 * <p>
 * Created by Luciano on 08/10/2015.
 */
public class MainExpandableAdapter extends BaseExpandableListAdapter {

    private HashMap<String, List<String>> mHashMapDados;
    private List<String> keys;

    public MainExpandableAdapter(HashMap<String, List<String>> dados) {
        mHashMapDados = dados;
        keys = new ArrayList<>(mHashMapDados.keySet());
    }

    @Override
    public int getGroupCount() {
        return keys != null ? keys.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mHashMapDados != null ? mHashMapDados.get(keys.get(groupPosition)).size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return keys.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mHashMapDados.get(keys.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {

            mViewHolder = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_expandable_list_item_1, null);

            mViewHolder.mTextView = (TextView) convertView.findViewById(android.R.id.text1);

            mViewHolder.mTextView.setTextColor(Color.WHITE);
            int left = mViewHolder.mTextView.getPaddingStart();
            mViewHolder.mTextView.setPadding(left, 20, 10, 20);
            mViewHolder.mTextView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.indigo200));

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTextView.setText(keys.get(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {

            mViewHolder = new ViewHolder();

            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);

            mViewHolder.mTextView = (TextView) convertView.findViewById(android.R.id.text1);

            mViewHolder.mTextView.setPadding(mViewHolder.mTextView.getPaddingStart() + 15,
                    mViewHolder.mTextView.getPaddingTop(),
                    mViewHolder.mTextView.getPaddingRight(), mViewHolder.mTextView.getPaddingBottom());

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTextView.setText(mHashMapDados.get(keys.get(groupPosition)).get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {
        TextView mTextView;
    }

}
