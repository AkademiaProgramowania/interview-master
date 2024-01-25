package pl.pop.interview.master.question;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( path = "api/v1/questions" )
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController( QuestionService questionService ) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public void addNewQuestion( @RequestBody QuestionDTO questionDTO ) {
        questionService.addNewQuestion( questionDTO );
    }

    @GetMapping("/list")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
