package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pop.interview.master.question.QuestionDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/answers")
class AnswerController {
    private final AnswerFacade answerFacade;

    // random question for user
    @GetMapping("/question")
    ResponseEntity<QuestionDTO> getRandomQuestion() {
        QuestionDTO questionDTO = answerFacade.findRandomQuestion();
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PostMapping("/{questionId}")
    ResponseEntity<String> save(@PathVariable Long questionId, @RequestBody AnswerDTO answerDTO) {
        AnswerDTO newAnswer = answerManager.save(questionId, answerDTO.isAnswer());
        return new ResponseEntity<>(newAnswer.getResult(), HttpStatus.CREATED);
    }
}
