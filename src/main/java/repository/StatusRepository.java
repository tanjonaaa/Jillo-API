package repository;

import model.Status;

import java.util.List;

public interface StatusRepository {
    List<Status> all();
    Status oneById(int id);
    Status oneByName(String name);
    void save(Status status);
    void update(Status status);
    void delete(Status status);
}
