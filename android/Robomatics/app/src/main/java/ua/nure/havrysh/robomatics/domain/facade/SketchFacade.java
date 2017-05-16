package ua.nure.havrysh.robomatics.domain.facade;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.Sketch;
import ua.nure.havrysh.robomatics.domain.repository.SketchRepository;
import ua.nure.havrysh.robomatics.mapper.FirebaseSketchToUiMapper;
import ua.nure.havrysh.robomatics.ui.model.SketchUiModel;
import ua.nure.havrysh.robomatics.ui.model.UserUIModel;

public class SketchFacade {
    private final UserFacade userFacade;

    private final SketchRepository sketchRepository;

    public SketchFacade(UserFacade userFacade, SketchRepository sketchRepository) {
        this.userFacade = userFacade;
        this.sketchRepository = sketchRepository;
    }

    public Flowable<List<SketchUiModel>> getCurrentUserSketches() {
        return userFacade.getCurrentUser()
                .map(UserUIModel::getId)
                .flatMap(this::getUserSketches);
    }

    public Flowable<List<SketchUiModel>> getUserSketches(String userId) {
        return userFacade.getCurrentUser().map(UserUIModel::getSketches)
                .flatMap(sketchRepository::getUserSketches)
                .flatMap(Flowable::fromIterable)
                .map(new FirebaseSketchToUiMapper())
                .toList()
                .toFlowable();
    }
}
