package ua.nure.havrysh.robomatics.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.ui.model.UserUIModel;

public class UsersAdapter extends BaseRecyclerAdapter<UserUIModel> {
    public UsersAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder createViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }

    public class UserViewHolder extends BaseViewHolder<UserUIModel> {

        @BindView(R.id.user_name)
        TextView nameTextView;

        public UserViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_user);
        }

        @Override
        protected void initViews(UserUIModel item) {
            nameTextView.setText(item.getName());
        }
    }
}
