package com.example.assignment.service;

import com.example.assignment.entity.ChangeHistory;
import com.example.assignment.entity.Evaluation;
import com.example.assignment.entity.EvaluationId;
import com.example.assignment.entity.EvaluationList;
import com.example.assignment.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EvaluationListRepository evaluationListRepository;

    @Autowired
    private ChangeHistoryRepository changeHistoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private HgrnRepository hgrnRepository;

    public List<Evaluation> getMonthlyEvaluations(String month) {
        return evaluationRepository.findByScoreDateStartingWith(month);
    }

//    public List<Evaluation> getMonthlyEvaluationsByTeam(String month, String teamCode) {
//        return evaluationRepository.findByScoreDateStartingWithAndTeamCode(month, teamCode);
//    }

    public List<Evaluation> getMonthlyEvaluationsByTeam(String month, String teamCode) {
        return evaluationRepository.findByScoreDateStartingWithAndTeamCodeAndIsTargetFixedTrue(month, teamCode);
    }

    @Transactional
    public List<Evaluation> calculateAndSaveMonthlyRanking(String month, String teamCode) {
        List<Evaluation> evaluations;
        if (teamCode != null) {
            evaluations = evaluationRepository.findByScoreDateStartingWithAndTeamCodeAndIsTargetFixedTrue(month, teamCode);
        } else {
            evaluations = evaluationRepository.findByScoreDateStartingWithAndIsTargetFixedTrue(month);
        }

        // 점수 계산 및 정렬
        evaluations.sort((a, b) -> Integer.compare(b.getScore1() + b.getScore2(), a.getScore1() + a.getScore2()));

        // 순위 계산 및 설정
        int rank = 1;
        int sameRankCount = 1;
        int prevScore = -1;

        for (int i = 0; i < evaluations.size(); i++) {
            Evaluation eval = evaluations.get(i);
            int currentScore = eval.getScore1() + eval.getScore2();

            if (i > 0 && currentScore != prevScore) {
                rank += sameRankCount;
                sameRankCount = 1;
            } else if (i > 0) {
                sameRankCount++;
            }

            eval.setRanking(rank);
            prevScore = currentScore;
        }

        // 순위가 업데이트된 Evaluation 객체들을 저장
        return evaluationRepository.saveAll(evaluations);
    }

    public List<Evaluation> getMonthlyRankings(String month, String teamCode) {
        if (teamCode != null) {
            return evaluationRepository.findByScoreDateStartingWithAndTeamCodeAndIsTargetFixedTrueOrderByRankingAsc(month, teamCode);
        } else {
            return evaluationRepository.findByScoreDateStartingWithAndIsTargetFixedTrueOrderByRankingAsc(month);
        }
    }

    public List<ChangeHistory> getMonthlyChangeHistory(String month) {
        return changeHistoryRepository.findByScoreDateStartingWith(month);
    }

    public List<Evaluation> getEmployeeEvaluations(Integer employeeId) {
        return evaluationRepository.findByAgentId(employeeId);
    }

    @Transactional
    public void saveAndConfirmEvaluation(Evaluation evaluation) {
        Evaluation oldEvaluation = evaluationRepository.findById(new EvaluationId(evaluation.getScoreDate(), evaluation.getAgentId(), evaluation.getTeamCode())).orElse(null);

        if (oldEvaluation != null) {
            checkAndSaveChangeHistory(oldEvaluation, evaluation, "Score1", oldEvaluation.getScore1(), evaluation.getScore1());
            checkAndSaveChangeHistory(oldEvaluation, evaluation, "Score2", oldEvaluation.getScore2(), evaluation.getScore2());
            checkAndSaveChangeHistory(oldEvaluation, evaluation, "IsScoreFixed", oldEvaluation.getIsScoreFixed(), evaluation.getIsScoreFixed());
            checkAndSaveChangeHistory(oldEvaluation, evaluation, "IsTargetFixed", oldEvaluation.getIsTargetFixed(), evaluation.getIsTargetFixed());
        }

        evaluationRepository.save(evaluation);
    }

    private void checkAndSaveChangeHistory(Evaluation oldEvaluation, Evaluation newEvaluation, String changeValue, Object oldValue, Object newValue) {
        if (!Objects.equals(oldValue, newValue)) {
            ChangeHistory changeHistory = new ChangeHistory(
                    newEvaluation.getScoreDate(),
                    newEvaluation.getAgentId(),
                    oldValue.toString(),
                    newValue.toString(),
                    changeValue,
                    java.time.LocalDate.now().toString()
            );
            changeHistoryRepository.save(changeHistory);
        }
    }

    @Transactional
    public void confirmEvaluationTarget(Evaluation evaluation) {
        evaluation.setIsTargetFixed(true);
        evaluationRepository.save(evaluation);
    }

//    @Transactional
//    public void transferConfirmedEvaluationsToList(String month) {
//        List<Evaluation> confirmedEvaluations = evaluationRepository.findByScoreDateStartingWithAndIsScoreFixedTrueAndRankingIsNotNull(month);
//
//        for (Evaluation eval : confirmedEvaluations) {
//            EvaluationList evalList = new EvaluationList();
//            evalList.setScoreDate(eval.getScoreDate());
//            evalList.setAgentId(eval.getAgentId());
//            evalList.setTeamCode(eval.getTeamCode());
//            evalList.setTotalScore(eval.getScore1() + eval.getScore2());
//            evalList.setRanking(eval.getRanking());
//
//            evaluationListRepository.save(evalList);
//        }
//    }
@Transactional
public void transferConfirmedEvaluationsToList(String month) {
    // Step 1: Update all evaluations with isScoreFixed = false to true
    List<Evaluation> evaluationsToUpdate = evaluationRepository.findByScoreDateStartingWithAndIsScoreFixedFalse(month);
    for (Evaluation eval : evaluationsToUpdate) {
        eval.setIsScoreFixed(true);
        evaluationRepository.save(eval);

        // Optionally, you can add change history here
        ChangeHistory changeHistory = new ChangeHistory(
                eval.getScoreDate(),
                eval.getAgentId(),
                "false",
                "true",
                "IsScoreFixed",
                java.time.LocalDate.now().toString()
        );
        changeHistoryRepository.save(changeHistory);
    }

    // Step 2: Transfer all evaluations to evaluation_list
    List<Evaluation> allEvaluations = evaluationRepository.findByScoreDateStartingWithAndRankingIsNotNull(month);

    for (Evaluation eval : allEvaluations) {
        EvaluationList evalList = new EvaluationList();
        evalList.setScoreDate(eval.getScoreDate());
        evalList.setAgentId(eval.getAgentId());
        evalList.setTeamCode(eval.getTeamCode());
        evalList.setTotalScore(eval.getScore1() + eval.getScore2());
        evalList.setRanking(eval.getRanking());

        evaluationListRepository.save(evalList);
    }
}
}