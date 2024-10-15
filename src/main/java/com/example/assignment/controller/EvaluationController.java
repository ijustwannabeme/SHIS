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
    public ResponseEntity<List<Evaluation>> getMonthlyEvaluations(@PathVariable String month) {
        return ResponseEntity.ok(evaluationService.getMonthlyEvaluations(month));
    }

//    @GetMapping("/evaluations/{month}/rank")
//    public ResponseEntity<List<EvaluationList>> calculateMonthlyRanking(@PathVariable String month) {
//        return ResponseEntity.ok(evaluationService.calculateMonthlyRanking(month));
//    }

    @PostMapping("/evaluations/{month}/confirm")
    public ResponseEntity<Void> confirmMonthlyEvaluations(@PathVariable String month) {
        evaluationService.confirmMonthlyEvaluations(month);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/evaluations/{month}/changes")
    public ResponseEntity<List<ChangeHistory>> getMonthlyChangeHistory(@PathVariable String month) {
        return ResponseEntity.ok(evaluationService.getMonthlyChangeHistory(month));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

//    @GetMapping("/employees/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
//        return ResponseEntity.ok(employeeService.getEmployeeById(id));
//    }

    @GetMapping("/employees/{id}/evaluations")
    public ResponseEntity<List<Evaluation>> getEmployeeEvaluations(@PathVariable Integer id) {
        return ResponseEntity.ok(evaluationService.getEmployeeEvaluations(id));
    }

//    @PostMapping("/evaluations")
//    public ResponseEntity<Void> saveAndConfirmEvaluation(@RequestBody Evaluation evaluation) {
//        evaluationService.saveAndConfirmEvaluation(evaluation);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/evaluations/target")
    public ResponseEntity<Void> confirmEvaluationTarget(@RequestBody Evaluation evaluation) {
        evaluationService.confirmEvaluationTarget(evaluation);
        return ResponseEntity.ok().build();
    }
}