package service;

import model.ToBeIn;
import org.springframework.stereotype.Service;
import repository.ToBeInRepository;

import java.util.List;

@Service
public class ToBeInServiceImpl implements ToBeInService{
    private ToBeInRepository repository;

    public ToBeInServiceImpl(ToBeInRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ToBeIn> getAllToBeIns() {
        return this.repository.all();
    }

    @Override
    public ToBeIn getToBeInById(int id) {
        return this.repository.oneById(id);
    }

    @Override
    public ToBeIn addToBeIn(ToBeIn toBeIn) {
        ToBeIn foundToBeIn = this.repository.oneByProjectId(toBeIn.getIdProject());
        if(foundToBeIn.getId() != 0){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(toBeIn);
            return this.repository.oneByProjectId(toBeIn.getIdProject());
        }
    }

    @Override
    public ToBeIn updateToBeIn(ToBeIn toBeIn) {
        ToBeIn foundToBeIn = this.repository.oneById(toBeIn.getId());
        if(foundToBeIn.getId() != 0){
            foundToBeIn.setCreatedAt(toBeIn.getCreatedAt());
            foundToBeIn.setIdProject(toBeIn.getIdProject());
            foundToBeIn.setIdUser(toBeIn.getIdUser());
            this.repository.update(foundToBeIn);
            return this.repository.oneById(toBeIn.getId());
        }else {
            return null;
        }
    }

    @Override
    public void deleteToBeIn(int id) {
        ToBeIn foundToBeIn = this.repository.oneById(id);
        if(foundToBeIn.getId() != 0){
            this.repository.delete(foundToBeIn);
        }else {
            System.out.println("Suppression ratée");
        }
    }
}
