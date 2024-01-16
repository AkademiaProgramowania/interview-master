package pl.pop.interview.master.question;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( path = "api/v1/questions" )
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController( QuestionService questionService ) {
        this.questionService = questionService;
    }

    @PutMapping( "/" )
    public void addNewQuestion( @RequestBody QuestionDTO questionDTO ) {
        questionService.addNewQuestion( questionDTO );
    }
}
