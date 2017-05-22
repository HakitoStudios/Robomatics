package ua.nure.havrysh.robomatics.domain.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.User;

public class UserRepository extends BaseFirebaseRepository {

    private static final String USERS = "users";
    private static final String SKETCHES = "sketches";

    @NonNull
    public Flowable<String> addSketchToIndex(String userId, String sketchId) {
        return setData(true,
                FirebaseDatabase.getInstance().getReference(USERS).child(userId).child(SKETCHES).child(sketchId));
    }

    @NonNull
    public Flowable<String> push(@NonNull User user, String id) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(USERS).child(id);
        return setData(user, ref);
    }

    @NonNull
    public Flowable<List<User>> getAll() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(USERS);
        return getDataList(User.class, ref);
    }

    public Flowable<User> getUser(String id) {
        return getData(User.class, FirebaseDatabase.getInstance().getReference(USERS).child(id));
    }

    public Flowable<String> putTempUser(String userId){
        return setData("temp", ref(USERS).child(userId).child("temp"));
    }
}
