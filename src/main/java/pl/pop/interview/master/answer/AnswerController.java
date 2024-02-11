package pl.pop.interview.master.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/answers")
class AnswerController {
    private final AnswerFacade answerFacade;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerFacade.getAllAnswers();
    }
}
