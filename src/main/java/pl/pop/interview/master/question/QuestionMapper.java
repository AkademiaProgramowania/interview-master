package pl.pop.interview.master.question;

import eye2web.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class QuestionMapper {
    private ModelMapper modelMapper;

    public Question mapDtoToEntity(QuestionDTO dto) {
        return modelMapper.map(dto, Question.class);
    }

    public QuestionDTO mapEntityToDto(Question entity) {
        return modelMapper.map(entity, QuestionDTO.class);
    }
}

