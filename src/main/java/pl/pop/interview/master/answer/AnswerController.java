package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pop.interview.master.question.QuestionDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/answers")
public class AnswerController {
    private final AnswerService answerService;

    // random question for user
    @GetMapping("/question")
    public ResponseEntity<QuestionDTO> getRandomQuestion() {
        QuestionDTO questionDTO = answerService.findRandomQuestion();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addNewAnswer( @RequestBody AnswerDTO answerDTO) {
        AnswerDTO newAnswer = answerService.addNewAnswer(answerDTO);
        return new ResponseEntity<>(newAnswer.getResult(), HttpStatus.CREATED);
    }
}
