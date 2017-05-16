package ua.nure.havrysh.robomatics.presenter.view;

import java.util.List;

import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public interface MySketchesView extends View {
    void showSketches(List<SketchUiModel> sketches);
}
