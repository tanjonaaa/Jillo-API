package service;

import model.ToBeIn;
import org.springframework.stereotype.Service;
import repository.JdbcToBeInRepository;

import java.util.List;

@Service
public class ToBeInService {
    private JdbcToBeInRepository repository;

    public ToBeInService(JdbcToBeInRepository repository) {
        this.repository = repository;
    }
    public List<ToBeIn> getAllToBeIns() {
        return this.repository.all();
    }
    public ToBeIn getToBeInById(int id) {
        return this.repository.oneById(id);
    }
    public ToBeIn addToBeIn(ToBeIn toBeIn) {
        ToBeIn foundToBeIn = this.repository.oneByUniqueColumn(toBeIn.getIdProject());
        if(foundToBeIn != null){
            return null;
        }else {
            this.repository.save(toBeIn);
            return this.repository.oneByUniqueColumn(toBeIn.getIdProject());
        }
    }
    public ToBeIn updateToBeIn(ToBeIn toBeIn) {
        ToBeIn foundToBeIn = this.repository.oneById(toBeIn.getId());
        if(foundToBeIn != null){
            foundToBeIn.setCreatedAt(toBeIn.getCreatedAt());
            foundToBeIn.setIdProject(toBeIn.getIdProject());
            foundToBeIn.setIdUser(toBeIn.getIdUser());
            this.repository.update(foundToBeIn, foundToBeIn.getId());
            return this.repository.oneById(toBeIn.getId());
        }else {
            return null;
        }
    }
    public int deleteToBeIn(int id) {
        ToBeIn foundToBeIn = this.repository.oneById(id);
        if(foundToBeIn != null){
            this.repository.delete(id);
            return 1;
        }else {
            return 0;
        }
    }
}
