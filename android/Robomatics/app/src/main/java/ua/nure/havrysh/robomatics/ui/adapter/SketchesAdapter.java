package ua.nure.havrysh.robomatics.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public class SketchesAdapter extends BaseRecyclerAdapter<SketchUiModel> {

    private final OnSketchSelectedListener onSketchSelectedListener;

    public SketchesAdapter(Context context, OnSketchSelectedListener onSketchSelectedListener) {
        super(context);
        this.onSketchSelectedListener = onSketchSelectedListener;
    }

    @Override
    protected BaseViewHolder createViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new SketchHolder(parent);
    }

    public interface OnSketchSelectedListener {
        void onSketchSelected(SketchUiModel sketch);
    }

    class SketchHolder extends BaseViewHolder<SketchUiModel> {

        @BindView(R.id.sketch_name_text_view)
        TextView sketchNameTextView;

        @BindView(R.id.author_text_view)
        TextView authorTextView;

        @BindView(R.id.code_lines_text_view)
        TextView codeLinesTextView;

        @BindView(R.id.likes_text_view)
        TextView likesTextView;

        public SketchHolder(ViewGroup parent) {
            super(parent, R.layout.item_sketch);
        }

        @Override
        protected void initViews(SketchUiModel item) {
            sketchNameTextView.setText(item.getName());
            authorTextView.setText(item.getAuthorName());
            codeLinesTextView.setText(String.valueOf(item.getLinesCount()));
            likesTextView.setText(String.valueOf(item.getLikes()));
            itemView.setOnClickListener(v -> onSketchSelectedListener.onSketchSelected(item));
        }
    }
}
