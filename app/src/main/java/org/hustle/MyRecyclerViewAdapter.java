package org.hustle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private JSONArray jsonArray;
    private LayoutInflater mInflater;

    MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        jsonArray = new JSONArray();
    }

    void addJsonObject(JSONObject jsonObject) {
        jsonArray.put(jsonObject);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject jsonObject = (JSONObject) jsonArray.get(position);
            holder.title.setText((String)jsonObject.get("title"));
            holder.text.setText((String)jsonObject.get("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (jsonArray == null) return 0;
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView text;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
