package ua.nure.havrysh.robomatics.domain.repository;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.Marker;

public abstract class BaseFirebaseRepository {

    protected Flowable<List<Marker>> getMarkers(DatabaseReference ref) {
        return Flowable.create(e -> {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Marker> list = new ArrayList<Marker>();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        list.add(new Marker(data.getKey()));
                    }
                    e.onNext(list);
                    e.onComplete();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(databaseError.toException());
                }
            });
        }, BackpressureStrategy.BUFFER);
    }

    protected <T> Flowable<List<T>> getDataList(Class<T> clazz, DatabaseReference ref) {
        return Flowable.create(e -> {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<T> list = new ArrayList<T>();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        list.add(data.getValue(clazz));
                    }
                    e.onNext(list);
                    e.onComplete();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(databaseError.toException());
                }
            });
        }, BackpressureStrategy.BUFFER);
    }

    protected <T> Flowable<T> getData(Class<T> clazz, DatabaseReference ref) {
        return Flowable.create(e -> {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    e.onNext(dataSnapshot.getValue(clazz));
                    e.onComplete();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(databaseError.toException());
                }
            });
        }, BackpressureStrategy.BUFFER);
    }

    protected <T> Flowable<T> setData(T data, DatabaseReference ref) {
        return Flowable.create(e -> {
            ref.setValue(data).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    e.onNext(data);
                    e.onComplete();
                } else {
                    e.onError(new RuntimeException("Can't set data"));
                }
            });
        }, BackpressureStrategy.BUFFER);

    }
}
