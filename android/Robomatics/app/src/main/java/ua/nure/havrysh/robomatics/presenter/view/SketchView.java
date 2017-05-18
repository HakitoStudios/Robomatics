package ua.nure.havrysh.robomatics.presenter.view;

import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public interface SketchView extends View {
    void showSketch(SketchUiModel sketchUiModel);

    void setOwn(boolean own);
}
