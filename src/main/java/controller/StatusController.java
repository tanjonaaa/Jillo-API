package controller;

import model.Status;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Status>> getAllStatus(){
        return ResponseEntity.ok(this.service.getAllStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable int id){
        Status status = this.service.getStatusById(id);
        if(status == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(status);
    }

    @PostMapping("")
    public ResponseEntity<Status> addStatus(@RequestBody Status status){
        Status createdStatus = this.service.addStatus(status);
        if(createdStatus == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createdStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable int id, @RequestBody Status status){
        status.setId(id);
        Status updatedStatus = this.service.updateStatus(status);
        if(updatedStatus == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteStatus(@PathVariable int id){
        int deletionStatus = this.service.deleteStatus(id);
        if(deletionStatus == 0){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
