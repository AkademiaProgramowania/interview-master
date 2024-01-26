package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pop.interview.master.question.Question;
import pl.pop.interview.master.question.QuestionDTO;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    // random question for user
    @GetMapping("/question")
    public ResponseEntity<QuestionDTO> getRandomQuestion() {
        Question randomQuestion = answerService.generateRandomQuestion();
        QuestionDTO questionDTO = new QuestionDTO(randomQuestion.getId(), randomQuestion.getContent());
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/answers")
    public ResponseEntity<String> saveNewAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        AnswerDTO newAnswer = answerService.saveNewAnswer(id, answerDTO.getAnswer());
        return new ResponseEntity<>(newAnswer.getResult(), HttpStatus.CREATED);
    }
}
