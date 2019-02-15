package com.sc.studentcorner.api;

import com.sc.studentcorner.model.Answer;
import com.sc.studentcorner.model.Question;
import com.sc.studentcorner.model.exception.QuestionNotFoundExceitpion;
import com.sc.studentcorner.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/question")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return service.getAllQuestions();
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question){
        return service.saveQuestion(question);
    }

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) throws QuestionNotFoundExceitpion {
        return service.getQuestionById(id);
    }

    @PostMapping("/answer/save/{questionId}")
    private Answer saveAnswer(@RequestBody Answer answer,
                              @PathVariable Long questionId) throws QuestionNotFoundExceitpion {
        return service.saveAnswer(answer,questionId);
    }


}
