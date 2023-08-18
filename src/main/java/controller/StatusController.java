package controller;

import model.Status;
import org.springframework.web.bind.annotation.*;
import service.StatusService;

import java.util.List;
@RestController
@RequestMapping("/status")
public class StatusController {

    StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Status> getAllStatus(){
        return this.service.getAllStatus();
    }

    @GetMapping("/{id}")
    public Status getStatusById(@PathVariable int id){
        return this.service.getStatusById(id);
    }

    @PostMapping("")
    public Status addStatus(@RequestBody Status status){
        return this.service.addStatus(status);
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable int id, @RequestBody Status status){
        status.setId(id);
        return this.service.updateStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable int id){
        this.service.deleteStatus(id);
    }
}
