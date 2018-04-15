package com.vs.daggersample.ui.contributors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.vs.daggersample.GlideApp;
import com.vs.daggersample.R;
import com.vs.daggersample.data.entity.Contributor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valentyn on 14.04.2018.
 */
public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ViewHolder> {

    private Context mContext;
    private List<Contributor> mContributors;
    private OnContributorClickListener mListener;

    public ContributorsAdapter(Context context) {
        mContext = context;
        mContributors = new ArrayList<>();
    }

    public void setData(List<Contributor> contributors) {
        mContributors = contributors;
        notifyDataSetChanged();
    }

    public void setListener(OnContributorClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contributor, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Contributor contributor = mContributors.get(position);

        if (contributor == null) {
            return;
        }

        if (contributor.getLogin() != null) {
            holder.tvLogin.setText(contributor.getLogin());
        }

        GlideApp.with(mContext)
                .load(contributor.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivAvatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onContributorClick(contributor, holder.ivAvatar);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mContributors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar = itemView.findViewById(R.id.ivAvatar);
        TextView tvLogin = itemView.findViewById(R.id.tvLogin);

        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnContributorClickListener {
        void onContributorClick(@NotNull Contributor contributor, @NotNull ImageView ivAvatar);
    }

}
