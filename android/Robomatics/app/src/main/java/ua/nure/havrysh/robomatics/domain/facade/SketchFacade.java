package ua.nure.havrysh.robomatics.domain.facade;

import android.text.TextUtils;

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
                .flatMap(this::getUserSketches);
    }

    private SketchUiModel mapSketch(Sketch sketch, UserUIModel user) {
        SketchUiModel res = new FirebaseSketchToUiMapper().apply(sketch);
        res.setOwnerId(user.getId());
        res.setAuthorName(user.getName());
        return res;
    }

    public Flowable<SketchUiModel> getSketch(String id) {
        return sketchRepository.getSketch(id).map(new FirebaseSketchToUiMapper());
    }

    public Flowable<List<SketchUiModel>> getUserSketches(UserUIModel user) {
        return Flowable.just(user.getSketches())
                .flatMap(sketchRepository::getUserSketches)
                .flatMap(Flowable::fromIterable)
                .map(sketch -> mapSketch(sketch, user))
                .toList()
                .toFlowable();
    }

    public Flowable<String> saveSketch(String id, String title, String code) {
        Sketch sketch = new Sketch();
        sketch.setId(id);
        sketch.setTitle(title);
        sketch.setCode(code);
        return sketchRepository.putSketch(sketch).flatMap(sketchId -> {
            if (TextUtils.isEmpty(id)) {
                return userFacade.addSketchToIndex(sketchId);
            }
            return Flowable.just(sketchId);
        });
    }
}
