package ua.nure.havrysh.robomatics.domain.facade;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.User;
import ua.nure.havrysh.robomatics.domain.repository.UserRepository;
import ua.nure.havrysh.robomatics.mapper.FirebaseUserToUIMapper;
import ua.nure.havrysh.robomatics.ui.model.UserUIModel;
import ua.nure.havrysh.robomatics.utils.exception.UnathorizedException;

public class UserFacade {
    private final UserRepository userRepository;

    public UserFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flowable<List<User>> getAllUsers() {
        return userRepository.getAll();
    }

    public Flowable<UserUIModel> getCurrentUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Flowable.error(new UnathorizedException());
        }
        String id = firebaseUser.getUid();
        return Flowable.just(firebaseUser)
                .map(new FirebaseUserToUIMapper())
                .doOnNext(user -> {
                    User u = userRepository.getUser(id).blockingFirst();
                    user.setSketches(u.getSketches());
                });
    }
}
