package pl.pop.interview.master.question;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "api/v1/questions" )
class QuestionController {
    private final QuestionManager questionManager;

    public QuestionController( QuestionManager questionManager) {
        this.questionManager = questionManager;
    }

    @PostMapping
    public void addNewQuestion( @RequestBody QuestionDTO questionDTO ) {
        questionManager.addNewQuestion( questionDTO );
    }

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionManager.getAllQuestions();
    }
}
