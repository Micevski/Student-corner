package com.sc.studentcorner.service;

import com.sc.studentcorner.model.Answer;
import com.sc.studentcorner.model.Question;
import com.sc.studentcorner.model.exception.QuestionNotFoundExceitpion;
import com.sc.studentcorner.repository.AnswerRepository;
import com.sc.studentcorner.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final AnswerRepository answerRepository;

    public QuestionService(QuestionRepository repository, AnswerRepository answerRepository) {
        this.repository = repository;
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Question saveQuestion(Question question){
        question.setAnswers(new LinkedList<>());
        return repository.save(question);
    }

    public List<Question> getAllQuestions(){
        return repository.findAll();
    }

    public Question getQuestionById(Long id) throws QuestionNotFoundExceitpion {
        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundExceitpion(String.format("Question with id [{%d}] was not found", id)));
    }

    @Transactional
    public Answer saveAnswer(Answer answer, Long questionId) throws QuestionNotFoundExceitpion {
        Question question = repository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundExceitpion(String.format("Question with id [{%d}] was not found", questionId)));
        question.getAnswers().add(answer);
        return answerRepository.save(answer);
    }
}
