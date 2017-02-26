package com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.luanluuhauigmail.luanluu.amthucvietnam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuanLuu on 12/10/2016.
 */
public class RowLocationAdapter extends ArrayAdapter<String> {

    private Context context;
    private int resoucre;
    private List<String> locations, tempItems,suggestions;

    public RowLocationAdapter(Context context, List<String> location) {
        super(context, 0);
        this.context = context;
        this.locations = location;
        this.tempItems = new ArrayList<>(location);
        this.suggestions = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_search_map, parent, false);

        TextView tv = (TextView) view.findViewById(R.id.tv_nam_location);
        tv.setText(this.locations.get(position));

        return view;
    }

    @Override
    public Filter getFilter() {
        return name_filter;
    }

    private Filter name_filter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return resultValue.toString();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (String i : tempItems) {
                    suggestions.add(i);
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            }else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<String> filterList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (String i : filterList) {
                    add(i);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
