package com.example.asassa.bakingapp3.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asassa.bakingapp3.R;
import com.example.asassa.bakingapp3.Utils.Step;

import java.util.List;

/**
 * Created by ASassa on 03.01.2018.
 */

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepHolder> {

    List<Step> mSteps = null;

    Context mContext;

    public interface OnStepClickListener
    {
        void onStepClick(Step step);
    }

    private OnStepClickListener stepClickListener;


    public StepListAdapter(Context context, List<Step> steps)
    {
        mContext = context;
        mSteps = steps;
        stepClickListener = (OnStepClickListener)context;
    }

    @Override
    public StepHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rowView = inflater.inflate(R.layout.step_item,parent,false);
        return new StepHolder(rowView);
    }

    @Override
    public void onBindViewHolder(StepHolder holder, int position) {
        Step step = mSteps.get(position);
        holder.textView.setText(step.shortDescription());
    }

    @Override
    public int getItemCount() {
        if (mSteps != null) return mSteps.size();
        return 0;
    }

    public class StepHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public StepHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_step_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stepClickListener.onStepClick(mSteps.get(getAdapterPosition()));
                }
            });
        }
    }
}
