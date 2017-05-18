package ua.nure.havrysh.robomatics.domain.repository;

import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.Sketch;

public class SketchRepository extends BaseFirebaseRepository {
    private static final String SKETCHES_NODE = "sketches";

    public Flowable<String> putSketch(Sketch sketch) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(SKETCHES_NODE);
        ref = TextUtils.isEmpty(sketch.getId()) ? ref.push() : ref.child(sketch.getId());
        return setData(sketch, ref);
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
