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

    public Flowable<String> addSketchToIndex(String sketchId) {
        return getCurrentUser().flatMap(user -> userRepository.addSketchToIndex(user.getId(), sketchId));
    }

    public Flowable<UserUIModel> getCurrentUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return Flowable.error(new UnathorizedException());
        }
        String id = firebaseUser.getUid();
        return Flowable.just(firebaseUser)
                .map(new FirebaseUserToUIMapper())
                .zipWith(userRepository.getUser(id)
                                .onErrorResumeNext(Flowable.just(new User())),
                        (userUIModel, user) -> {
                            userUIModel.setSketches(user.getSketches());
                            return userUIModel;
                        });
    }

    public Flowable<String> checkUserForRegistration(String userId) {
        return userRepository.getUser(userId).onErrorResumeNext(userRepository.putTempUser(userId).map(s -> new User())).map(user -> userId);
    }
}
