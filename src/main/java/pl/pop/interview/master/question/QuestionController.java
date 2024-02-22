package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pop.interview.master.answer.AnswerDTO;
import pl.pop.interview.master.answer.AnswerFacade;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/questions")
class QuestionController {
    private final QuestionFacade questionFacade;
    private final AnswerFacade answerFacade;

    @PostMapping
    public void addNewQuestion(@RequestBody QuestionDTO questionDTO) {
        questionFacade.addNewQuestion(questionDTO);
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionFacade.getAllQuestions();
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<Page<QuestionDTO>> getQuestionsForPractitioner(@PathVariable("id") Long id,
                                                         @RequestParam(required = false, defaultValue = "false")
                                                         Boolean answered, Pageable pageable) {
        return ResponseEntity.ok(questionFacade.getQuestionsForPractitioner(id,answered, pageable));
    }

    @GetMapping("/question")
    ResponseEntity<QuestionDTO> getRandomQuestion() {
        QuestionDTO questionDTO = questionFacade.findRandomQuestion();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PostMapping("/answers")
    public ResponseEntity<String> addNewAnswer( @RequestBody AnswerDTO answerDTO) {
        AnswerDTO newAnswer = answerFacade.addNewAnswer(answerDTO);
        return new ResponseEntity<>(newAnswer.getResult(), HttpStatus.CREATED);
    }
}
