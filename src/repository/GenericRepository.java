package repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szkutek on 12.06.17.
 */
public class GenericRepository<ID, T extends Serializable> implements Serializable {
    protected Map<ID, T> items = new HashMap<>();

    public T getById(ID id) {
        return items.get(id);
    }

    public void addItem(ID id, T item) {
        items.put(id, item);
    }

    public void update(ID id, T item) {
        items.replace(id, item);
    }

    public void remove(ID id) {
        items.remove(id);
    }

    public Collection<T> getAll() {
        return Collections.unmodifiableCollection(items.values());
    }
}
