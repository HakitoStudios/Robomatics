package ua.nure.havrysh.robomatics.domain.repository;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import ua.nure.havrysh.robomatics.domain.model.Marker;
import ua.nure.havrysh.robomatics.utils.exception.ItemNotFoundException;

public abstract class BaseFirebaseRepository {

    private static final String ID_FIELD = "id";

    protected DatabaseReference ref(String child) {
        return FirebaseDatabase.getInstance().getReference(child);
    }

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
                        T val = data.getValue(clazz);
                        populateIdIfNeeded(val, data.getKey());
                        list.add(val);
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

    private void populateIdIfNeeded(Object item, String id) {
        try {
            Field f = item.getClass().getDeclaredField(ID_FIELD);
            f.setAccessible(true);
            if (f != null && f.getType() == String.class) {
                f.set(item, id);
            }
        } catch (NoSuchFieldException e1) {
            Log.d(getClass().getSimpleName(), "Current object has no Id field");
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    protected <T> Flowable<T> getData(Class<T> clazz, DatabaseReference ref) {
        return Flowable.create(e -> {
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    T val = dataSnapshot.getValue(clazz);
                    if (val == null) {
                        e.onError(new ItemNotFoundException(ref.toString()));
                        return;
                    }
                    populateIdIfNeeded(val, ref.getKey());
                    e.onNext(val);
                    e.onComplete();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    e.onError(databaseError.toException());
                }
            });
        }, BackpressureStrategy.BUFFER);
    }

    protected <T> Flowable<String> setData(T data, DatabaseReference ref) {
        return Flowable.create(e -> {
            try {
                Field f = data.getClass().getDeclaredField(ID_FIELD);
                f.setAccessible(true);
                if (f != null && f.getType() == String.class) {
                    f.set(data, null);
                }
            } catch (NoSuchFieldException e1) {
                //none
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            ref.setValue(data).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    e.onNext(ref.getKey());
                    e.onComplete();
                } else {
                    e.onError(new RuntimeException("Can't set data"));
                }
            });
        }, BackpressureStrategy.BUFFER);

    }
}
