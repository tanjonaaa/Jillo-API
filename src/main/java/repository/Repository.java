package repository;

import model.Status;

import java.util.List;

public interface Repository<T> {
    List<T> all();
    T oneById(int id);
    T oneByUniqueField(String uniqueField);
    void save(T t);
    void update(T t);
    void delete(T t);
}
