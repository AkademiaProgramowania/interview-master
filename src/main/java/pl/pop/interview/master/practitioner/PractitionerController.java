package pl.pop.interview.master.practitioner;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/practitioners")
class PractitionerController {

    private final PractitionerManager practitionerManager;

    @GetMapping
    List<PractitionerDTO> listPractitioners() {
        return practitionerManager.listPractitioners();
    }

}
