package ua.nure.havrysh.robomatics.utils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

public class ListMapper<T, R> implements Function<List<T>, List<R>> {
    private final Function<T, R> mapper;

    public ListMapper(Function<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<R> apply(@NonNull List<T> list) throws Exception {
        return Flowable.fromIterable(list).map(mapper).toList().blockingGet();
    }
}
