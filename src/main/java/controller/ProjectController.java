package controller;

import model.Project;
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
    public List<Project> getAllProjects(){
        return this.service.getAllProjects();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable int id){
        return this.service.getProjectById(id);
    }

    @PostMapping("")
    public Project addProject(@RequestBody Project project){
        return this.service.addProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable int id, @RequestBody Project project){
        project.setId(id);
        return this.service.updateProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id){
        this.service.deleteProject(id);
    }
}
