package ua.nure.havrysh.robomatics.domain.repository;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.Marker;
import ua.nure.havrysh.robomatics.domain.model.Sketch;

public class SketchRepository extends BaseFirebaseRepository {
    private static final String SKETCHES_NODE = "sketches";

    private static final String LIKES_NODE = "likes";

    public Flowable<List<Marker>> getLikes(String sketchId) {
        return getMarkers(FirebaseDatabase.getInstance().getReference(LIKES_NODE).child(sketchId));
    }

    public Flowable<Sketch> putSketch(Sketch sketch) {
        return setData(sketch, FirebaseDatabase.getInstance().getReference(SKETCHES_NODE).push());
    }

    public Flowable<Sketch> getSketch(String id) {
        return getData(Sketch.class,
                FirebaseDatabase.getInstance().getReference(SKETCHES_NODE).child(id));
    }

    public Flowable<List<Sketch>> getUserSketches(List<String> userSketches) {
        return Flowable.fromIterable(userSketches)
                .flatMap(this::getSketch)
                .toList()
                .toFlowable();
    }
}
