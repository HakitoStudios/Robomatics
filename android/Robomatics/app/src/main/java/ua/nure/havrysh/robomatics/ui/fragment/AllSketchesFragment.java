package ua.nure.havrysh.robomatics.ui.fragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ua.nure.havrysh.robomatics.R;
import ua.nure.havrysh.robomatics.di.component.ActivityComponent;
import ua.nure.havrysh.robomatics.presenter.AllSketchesPresenter;
import ua.nure.havrysh.robomatics.presenter.BasePresenter;
import ua.nure.havrysh.robomatics.presenter.MySketchesPresenter;
import ua.nure.havrysh.robomatics.presenter.view.AllSketchesView;
import ua.nure.havrysh.robomatics.ui.adapter.SketchesAdapter;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public class AllSketchesFragment extends BaseFragment implements AllSketchesView{

    @Inject
    AllSketchesPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.no_items_text)
    TextView noItemsText;

    SketchesAdapter adapter;

    public static AllSketchesFragment newInstance() {
        Bundle args = new Bundle();
        AllSketchesFragment fragment = new AllSketchesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        adapter = new SketchesAdapter(getContext(), presenter::onSketchSelected);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.initView();
    }

    @Override
    protected void setupToolbar(Toolbar toolbar, ActionBar actionBar) {
        super.setupToolbar(toolbar, actionBar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("All sketches");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_sketches;
    }

    @Override
    protected BasePresenter injectDependencies(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        return presenter;
    }

    @Override
    public void showSketches(List<SketchUiModel> sketches) {
        adapter.swapItems(sketches);
    }
}
