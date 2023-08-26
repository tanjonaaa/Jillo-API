package service;

import model.Status;
import org.springframework.stereotype.Service;
import repository.JdbcStatusRepository;

import java.util.List;
@Service
public class StatusServiceImpl implements StatusService{
    private final JdbcStatusRepository repository;

    public StatusServiceImpl(JdbcStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Status> getAllStatus() {
        return this.repository.all();
    }

    @Override
    public Status getStatusById(int id) {
        return this.repository.oneById(id);
    }

    @Override
    public Status addStatus(Status status) {
        Status foundStatus = this.repository.oneByUniqueColumn(status.getName());
        if(foundStatus != null){
            return null;
        }else {
            this.repository.save(status);
            return this.repository.oneByUniqueColumn(status.getName());
        }
    }

    @Override
    public Status updateStatus(Status status) {
        Status foundStatus = this.repository.oneById(status.getId());
        if(foundStatus != null){
            foundStatus.setName(status.getName());
            this.repository.update(foundStatus, foundStatus.getId());
            return this.repository.oneById(status.getId());
        }else {
            return null;
        }
    }

    @Override
    public int deleteStatus(int id) {
        Status foundStatus = this.repository.oneById(id);
        if(foundStatus != null){
            this.repository.delete(id);
            return 1;
        }else {
            return 0;
        }
    }
}
