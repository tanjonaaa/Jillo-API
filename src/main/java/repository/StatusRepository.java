package repository;

import model.Status;

import java.util.List;

public interface StatusRepository {
    List<Status> all();
    Status oneById(int id);
    Status oneByTitle(String title);
    void save(Status status);
    void update(Status status);
    void delete(Status status);
}
