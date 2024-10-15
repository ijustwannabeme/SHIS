package com.example.assignment.service;

import com.example.assignment.entity.ChangeHistory;
import com.example.assignment.entity.Evaluation;
import com.example.assignment.entity.EvaluationId;
import com.example.assignment.entity.EvaluationList;
import com.example.assignment.repository.ChangeHistoryRepository;
import com.example.assignment.repository.EvaluationListRepository;
import com.example.assignment.repository.EvaluationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationListRepository evaluationListRepository;

    @Autowired
    private ChangeHistoryRepository changeHistoryRepository;

    public List<Evaluation> getMonthlyEvaluations(String month) {
        return evaluationRepository.findByScoreDateStartingWith(month);
    }

//    @Transactional
//    public List<EvaluationList> calculateMonthlyRanking(String month) {
//        List<Evaluation> evaluations = getMonthlyEvaluations(month);
//        List<EvaluationList> rankings = evaluations.stream()
//                .map(e -> new EvaluationList(e.getScoreDate(), e.getAgentId(), e.getTeamCode(),
//                        e.getScore1() + e.getScore2(), 0))
//                .sorted((a, b) -> b.getTotalScore().compareTo(a.getTotalScore()))
//                .collect(Collectors.toList());
//
//        for (int i = 0; i < rankings.size(); i++) {
//            rankings.get(i).setRanking(i + 1);
//        }
//
//        return evaluationListRepository.saveAll(rankings);
//    }

    @Transactional
    public void confirmMonthlyEvaluations(String month) {
        List<Evaluation> evaluations = getMonthlyEvaluations(month);
        evaluations.forEach(e -> e.setIsScoreFixed(true));
        evaluationRepository.saveAll(evaluations);
    }

    public List<ChangeHistory> getMonthlyChangeHistory(String month) {
        return changeHistoryRepository.findByScoreDateStartingWith(month);
    }

    public List<Evaluation> getEmployeeEvaluations(Integer employeeId) {
        return evaluationRepository.findByAgentId(employeeId);
    }

//    @Transactional
//    public void saveAndConfirmEvaluation(Evaluation evaluation) {
//        Evaluation oldEvaluation = evaluationRepository.findById(new EvaluationId(evaluation.getScoreDate(), evaluation.getAgentId(), evaluation.getTeamCode())).orElse(null);
//
//        if (oldEvaluation != null) {
//            ChangeHistory changeHistory = new ChangeHistory(evaluation.getScoreDate(), evaluation.getAgentId(),
//                    oldEvaluation.getScore1().toString(), evaluation.getScore1().toString(), "Score1", java.time.LocalDate.now().toString());
//            changeHistoryRepository.save(changeHistory);
//        }
//
//        evaluation.setIsScoreFixed(true);
//        evaluationRepository.save(evaluation);
//    }

    @Transactional
    public void confirmEvaluationTarget(Evaluation evaluation) {
        evaluation.setIsTargetFixed(true);
        evaluationRepository.save(evaluation);
    }
}