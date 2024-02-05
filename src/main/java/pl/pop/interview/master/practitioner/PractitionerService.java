package pl.pop.interview.master.practitioner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PractitionerService {

    private final PractitionerRepository practitionerRepository;

    public Practitioner createNewPractitioner() {
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
