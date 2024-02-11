package pl.pop.interview.master.question;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/questions")
class QuestionController {
    private final QuestionFacade questionFacade;

    @PostMapping
    public void addNewQuestion(@RequestBody QuestionDTO questionDTO) {
        questionFacade.addNewQuestion(questionDTO);
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionFacade.getAllQuestions();
    }
}
