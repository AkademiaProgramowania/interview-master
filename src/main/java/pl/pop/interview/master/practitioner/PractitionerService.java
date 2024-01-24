package pl.pop.interview.master.practitioner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;

    public Practitioner createNewPractitioner() {
        // create a new practitioner called only in creating new account
        Practitioner practitioner = new Practitioner();
        return practitionerRepository.save(practitioner);
    }

    public List<PractitionerDTO> listPractitioners() {
        return practitionerRepository.findAll()
                .stream()
                .map(practitioner -> buildPractitionerDTOFromPractitioner(practitioner))
                .toList();
    }

    private PractitionerDTO buildPractitionerDTOFromPractitioner(Practitioner practitioner) {
        PractitionerDTO practitionerDTO = new PractitionerDTO();
        practitionerDTO.setId(practitioner.getId());
        return practitionerDTO;
    }

    private Practitioner buildPractitionerFromPractitionerDTO(PractitionerDTO practitionerDTO) {
        return new Practitioner(practitionerDTO.getId());
    }
}
