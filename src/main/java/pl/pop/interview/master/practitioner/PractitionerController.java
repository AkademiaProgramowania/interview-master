package pl.pop.interview.master.practitioner;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/practitioners")
class PractitionerController {

    private final PractitionerService practitionerService;

    @GetMapping
    List<PractitionerDTO> listPractitioners() {
        return practitionerService.listPractitioners();
    }

}
