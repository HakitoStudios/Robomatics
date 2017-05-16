package ua.nure.havrysh.robomatics.domain.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.User;

public class UserRepository extends BaseFirebaseRepository {

    private static final String USERS = "users";

    @NonNull
    public Flowable<User> push(@NonNull User user) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(USERS).push();
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
}
