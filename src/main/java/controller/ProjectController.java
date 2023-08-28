package controller;

import model.Project;
import model.Task;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProjectServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/projects")
public class ProjectController {

    ProjectServiceImpl service;

    public ProjectController(ProjectServiceImpl service) {
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
        }
        return ResponseEntity.ok(createdProject);
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

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> getContributors(@PathVariable int id){
        return ResponseEntity.ok(this.service.getCollaborators(id));
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable int id){
        return ResponseEntity.ok(this.service.getTasks(id));
    }

    @GetMapping("/{id}/status/{statusId}/tasks")
    public ResponseEntity<List<Task>> getTasksByStatus(
            @PathVariable int id, @PathVariable int statusId
    ){
        return ResponseEntity.ok(this.service.getTasksByStatus(id, statusId));
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<List<User>> addCollaborator(
            @PathVariable int id,
            @RequestBody int idUser
    ){
        List<User> previousCollaborators = this.service.getCollaborators(id);
        this.service.addCollaborator(id, idUser);
        List<User> nextCollaborators = this.service.getCollaborators(id);
        if(nextCollaborators.size() <= previousCollaborators.size()){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(nextCollaborators);
    }
}
