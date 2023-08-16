package service;

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
        return null;
    }

    @Override
    public Status getStatusById(int id) {
        return null;
    }

    @Override
    public Status addStatus(Status status) {
        return null;
    }

    @Override
    public Status updateStatus(Status status) {
        return null;
    }

    @Override
    public void deleteStatus(int id) {

    }
}
