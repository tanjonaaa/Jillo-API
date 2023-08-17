package service;

import model.Project;
import model.Status;
import org.springframework.stereotype.Service;
import repository.StatusRepository;
import java.util.List;
@Service
public class StatusServiceImpl implements StatusService{
    private StatusRepository repository;

    public StatusServiceImpl(StatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Status> getAllStatus() {
        return this.repository.all();
    }

    @Override
    public Status getStatusById(int id) {
        Status foundStatus = this.repository.oneById(id);
        if(foundStatus.getId() != 0){
            return foundStatus;
        }else {
            return null;
        }
    }

    @Override
    public Status addStatus(Status status) {
        Status foundStatus = this.repository.oneByName(status.getName());
        if(foundStatus.getId() != 0){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(status);
            return this.repository.oneByName(status.getName());
        }
    }

    @Override
    public Status updateStatus(Status status) {
        Status foundStatus = this.repository.oneById(status.getId());
        if(foundStatus.getId() != 0){
            foundStatus.setName(status.getName());
            this.repository.update(foundStatus);
            return this.repository.oneById(status.getId());
        }else {
            return null;
        }
    }

    @Override
    public void deleteStatus(int id) {
        Status foundStatus = this.repository.oneById(id);
        if(foundStatus.getId() != 0){
            this.repository.delete(foundStatus);
        }else {
            System.out.println("Suppression ratée");
        }
    }
}
