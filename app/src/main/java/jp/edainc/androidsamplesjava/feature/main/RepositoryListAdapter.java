package jp.edainc.androidsamplesjava.feature.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jp.edainc.androidsamplesjava.R;
import jp.edainc.androidsamplesjava.databinding.ViewRepositoryListItemBinding;

/**
 * Created by kobayashiryou on 2017/10/26.
 *
 * リポジトリのリスト
 */

class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListAdapter.ViewHolder> {

    interface OnClickItemListener {
        void onClick(RepositoryListItem item);
    }

    private LayoutInflater inflater;
    private final List<RepositoryListItem> items = new ArrayList<>();
    private OnClickItemListener clickItemListener;

    RepositoryListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    void setItems(List<RepositoryListItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    void setClickItemListener(OnClickItemListener listener) {
        clickItemListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.view_repository_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RepositoryListItem item;
        private ViewRepositoryListItemBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            binding.item.setOnClickListener(v -> {
                if(item != null && clickItemListener != null) {
                    clickItemListener.onClick(item);
                }
            });
        }

        void setItem(RepositoryListItem item) {
            this.item = item;
            binding.setItem(item);
        }
    }
}
