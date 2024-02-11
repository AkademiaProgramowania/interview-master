package pl.pop.interview.master.practitioner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PractitionerFacade {

    private final PractitionerRepository practitionerRepository;

    public Practitioner createNewPractitioner() {
        // create a new practitioner called only in creating new account
        Practitioner practitioner = new Practitioner();
        return practitionerRepository.save(practitioner);
    }


    public List<PractitionerDTO> listPractitioners() {
        return practitionerRepository.findAll()
                .stream()
                .map(PractitionerDTO::mapToDTO)
                .toList();
    }

    public Practitioner getPractitioner(Long practitionerId) {

        return practitionerRepository
                .findById( practitionerId )
                .orElseThrow( () ->
                        new PractitionerServiceException(
                                "Practitioner with ID " + practitionerId + " does not exist!"  )
                );
    }
}
