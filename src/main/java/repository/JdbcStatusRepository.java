package repository;

import model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JdbcStatusRepository implements StatusRepository{
    @Override
    public List<Status> all() {
        return null;
    }

    @Override
    public Status oneById(int id) {
        return null;
    }

    @Override
    public Status oneByTitle(String title) {
        return null;
    }

    @Override
    public void save(Status status) {

    }

    @Override
    public void update(Status status) {

    }

    @Override
    public void delete(Status status) {

    }
}
