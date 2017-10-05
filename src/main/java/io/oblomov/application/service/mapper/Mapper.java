package io.oblomov.application.service.mapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class Mapper<O, D> {

    abstract D map(final O o);

    protected Collection<D> map(final Collection<O> ts) {
        final Collection<D> entities = ts instanceof Set ? new HashSet<D>() : new ArrayList<D>();
        for (O t : ts) {
            entities.add(map(t));
        }
        return entities;
    }
}
