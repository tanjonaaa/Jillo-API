package controller;

import model.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProjectService;

import java.util.List;
@RestController
@RequestMapping("/projects")
public class ProjectController {

    ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(this.service.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id){
        Project project = this.service.getProjectById(id);
        if(project == null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(project);
        }
    }

    @PostMapping("")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        Project createdProject = this.service.addProject(project);
        if(createdProject == null){
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.ok(createdProject);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project project){
        project.setId(id);
        Project updatedProject = this.service.updateProject(project);
        if(updatedProject == null){
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.ok(project);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteProject(@PathVariable int id){
        int deleted = this.service.deleteProject(id);
        if(deleted == 0){
            return ResponseEntity.internalServerError().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }
}
