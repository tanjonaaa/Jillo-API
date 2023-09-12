package co.tanjona.man.jillo.service;

import co.tanjona.man.jillo.model.Status;
import org.springframework.stereotype.Service;
import co.tanjona.man.jillo.repository.JdbcStatusRepository;

import java.util.List;
@Service
public class StatusService {
    private final JdbcStatusRepository repository;
    public StatusService(JdbcStatusRepository repository) {
        this.repository = repository;
    }
    public List<Status> getAllStatus() {
        return this.repository.all();
    }
    public Status getStatusById(int id) {
        return this.repository.oneById(id);
    }
    public Status addStatus(Status status) {
        Status foundStatus = this.repository.oneByUniqueColumn(status.getName());
        if(foundStatus != null){
            return null;
        }else {
            this.repository.save(status);
            return this.repository.oneByUniqueColumn(status.getName());
        }
    }
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
