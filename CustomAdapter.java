package com.example.administrator.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TemplateModel> templateModelArrayList;

    public CustomAdapter(Context context, ArrayList<TemplateModel> templateModelArrayList) {

        this.context = context;
        this.templateModelArrayList = templateModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return templateModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return templateModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_view, null, true);

            holder.lvcategories = (TextView) convertView.findViewById(R.id.categories);
            holder.lvtitle = (TextView) convertView.findViewById(R.id.title);
            holder.lvauthor = (TextView) convertView.findViewById(R.id.author);
            holder.lvdate = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.lvcategories.setText(templateModelArrayList.get(position).getCategories());
        holder.lvtitle.setText(templateModelArrayList.get(position).getTitle());
        holder.lvauthor.setText(templateModelArrayList.get(position).getAuthor());
        holder.lvdate.setText(templateModelArrayList.get(position).getDate());

        return convertView;
    }

    private class ViewHolder {

        protected TextView lvcategories, lvtitle, lvauthor, lvdate;
    }

}
