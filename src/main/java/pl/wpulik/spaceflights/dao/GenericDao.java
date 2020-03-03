package pl.wpulik.spaceflights.dao;
import java.io.Serializable;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

	
    void save(T newObject);
    T get(PK primaryKey);
    void update(T updateObject);
    void remove(PK key);
    List<T> getAll();
}
