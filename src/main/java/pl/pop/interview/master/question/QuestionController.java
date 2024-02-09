package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pop.interview.master.answer.AnswerDTO;
import pl.pop.interview.master.answer.AnswerManager;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/questions")
class QuestionController {
    private final QuestionManager questionManager;
    private final AnswerManager answerManager;

    @PostMapping
    public void addNewQuestion(@RequestBody QuestionDTO questionDTO) {
        questionManager.addNewQuestion(questionDTO);
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionManager.getAllQuestions();
    }

    @GetMapping("/question")
    ResponseEntity<QuestionDTO> getRandomQuestion() {
        QuestionDTO questionDTO = questionManager.findRandomQuestion();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PostMapping("/answers")
    public ResponseEntity<String> addNewAnswer( @RequestBody AnswerDTO answerDTO) {
        AnswerDTO newAnswer = answerManager.addNewAnswer(answerDTO);
        return new ResponseEntity<>(newAnswer.getResult(), HttpStatus.CREATED);
    }
}
