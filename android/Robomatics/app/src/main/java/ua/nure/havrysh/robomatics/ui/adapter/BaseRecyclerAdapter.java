package ua.nure.havrysh.robomatics.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    private final List<T> items = new ArrayList<>();

    private final Context context;

    protected BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseRecyclerAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(LayoutInflater.from(context), parent, viewType);
    }

    protected abstract BaseRecyclerAdapter.BaseViewHolder createViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<T> newItems) {
        if (newItems == null) {
            return;
        }
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, int position) {
        holder.initViews(items.get(position));
    }

    public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        public BaseViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(LayoutInflater.from(context).inflate(layoutId, parent, false));
            ButterKnife.bind(this, itemView);
        }

        protected abstract void initViews(T item);
    }
}
