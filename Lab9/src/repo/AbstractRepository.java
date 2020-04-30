package repo;

import java.util.List;

public abstract class AbstractRepository<E> {
    public abstract void create(E e);
    public abstract E findById(int id);

    public abstract List<E> findByName(String name);
}
