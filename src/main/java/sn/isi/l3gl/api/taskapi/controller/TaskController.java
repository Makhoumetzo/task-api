package sn.isi.l3gl.api.taskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.taskcore.entity.Task;
import sn.isi.l3gl.core.taskcore.enums.TaskStatus;
import sn.isi.l3gl.core.taskcore.service.TaskService;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des tâches.
 * Aucune logique métier ici — uniquement des appels à TaskService (task-core).
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * POST /api/tasks
     * Créer une nouvelle tâche
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task created = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/tasks
     * Lister toutes les tâches
     */
    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    /**
     * PUT /api/tasks/{id}/status?status=DONE
     * Mettre à jour le statut d'une tâche
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Task updated = taskService.updateStatus(id, TaskStatus.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(updated);
    }

    /**
     * GET /api/tasks/done/count
     * Compter les tâches avec le statut DONE
     */
    @GetMapping("/done/count")
    public ResponseEntity<Long> countCompleted() {
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
}
