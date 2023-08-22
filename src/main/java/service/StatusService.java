package service;

import model.Status;

import java.util.List;

public interface StatusService {
    List<Status> getAllStatus();
    Status getStatusById(int id);
    Status addStatus(Status status);
    Status updateStatus(Status status);
    void deleteStatus(int id);
}
