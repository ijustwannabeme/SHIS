package com.example.assignment.controller;

import com.example.assignment.service.*;
import com.example.assignment.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/evaluations/{month}")
    public ResponseEntity<List<Evaluation>> getMonthlyEvaluations(
            @PathVariable String month,
            @RequestParam(required = false) String teamCode) {
        if (teamCode != null) {
            return ResponseEntity.ok(evaluationService.getMonthlyEvaluationsByTeam(month, teamCode));
        }
        return ResponseEntity.ok(evaluationService.getMonthlyEvaluations(month));
    }

    @PostMapping("/evaluations/{month}/rank")
    public ResponseEntity<List<Evaluation>> calculateMonthlyRanking(
            @PathVariable String month,
            @RequestParam(required = false) String teamCode) {
        return ResponseEntity.ok(evaluationService.calculateAndSaveMonthlyRanking(month, teamCode));
    }

    @GetMapping("/evaluations/{month}/rank")
    public ResponseEntity<List<Evaluation>> getMonthlyRankings(
            @PathVariable String month,
            @RequestParam(required = false) String teamCode) {
        return ResponseEntity.ok(evaluationService.getMonthlyRankings(month, teamCode));
    }

    @GetMapping("/evaluations/{month}/changes")
    public ResponseEntity<List<ChangeHistory>> getMonthlyChangeHistory(@PathVariable String month) {
        return ResponseEntity.ok(evaluationService.getMonthlyChangeHistory(month));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeByAgentId(id));
    }

    @GetMapping("/employees/{id}/evaluations")
    public ResponseEntity<List<Evaluation>> getEmployeeEvaluations(@PathVariable Integer id) {
        return ResponseEntity.ok(evaluationService.getEmployeeEvaluations(id));
    }

    @PostMapping("/evaluations")
    public ResponseEntity<Void> saveAndConfirmEvaluation(@RequestBody Evaluation evaluation) {
        evaluationService.saveAndConfirmEvaluation(evaluation);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/evaluations/target")
    public ResponseEntity<Void> confirmEvaluationTarget(@RequestBody Evaluation evaluation) {
        evaluationService.confirmEvaluationTarget(evaluation);
        return ResponseEntity.ok().build();
    }
}