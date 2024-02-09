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
    private final AnswerManager answerManager;

    // random question for user

}
