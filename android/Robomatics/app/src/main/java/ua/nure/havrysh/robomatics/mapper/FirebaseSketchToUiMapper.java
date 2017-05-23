package ua.nure.havrysh.robomatics.mapper;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import ua.nure.havrysh.robomatics.domain.model.Sketch;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;

public class FirebaseSketchToUiMapper implements Function<Sketch, SketchUiModel> {
    @Override
    public SketchUiModel apply(@NonNull Sketch sketch) {
        int lines = 1;
        for (char c : sketch.getCode().toCharArray()) {
            if (c == '\n') {
                lines++;
            }
        }
        return new SketchUiModel(sketch.getId(), sketch.getTitle(), sketch.getCode(), "", lines, "");
    }
}
