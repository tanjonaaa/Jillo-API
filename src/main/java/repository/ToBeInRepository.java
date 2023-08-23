package repository;

import model.ToBeIn;

import java.util.List;

public interface ToBeInRepository {
    List<ToBeIn> all();
    ToBeIn oneById(int id);
    ToBeIn oneByProjectId(int projectId);
    void save(ToBeIn toBeIn);
    void update(ToBeIn toBeIn);
    void delete(ToBeIn toBeIn);
}
