package ua.nure.havrysh.robomatics.mapper;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import ua.nure.havrysh.robomatics.ui.model.UserUIModel;

public class FirebaseUserToUIMapper implements Function<FirebaseUser, UserUIModel> {
    @Override
    public UserUIModel apply(@NonNull FirebaseUser user) throws Exception {
        return new UserUIModel(user.getDisplayName(),
                user.getUid(),
                user.getPhotoUrl() == null ? null : user.getPhotoUrl().toString(), new ArrayList<>());
    }
}
