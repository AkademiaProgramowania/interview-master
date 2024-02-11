package pl.pop.interview.master.practitioner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
class PractitionerManager implements PractitionerFacade {

    private final PractitionerRepository practitionerRepository;

    @Override
    public Practitioner createNewPractitioner() {
        // create a new practitioner called only in creating new account
        Practitioner practitioner = new Practitioner();
        return practitionerRepository.save(practitioner);
    }

    @Override
    public List<PractitionerDTO> listPractitioners() {
        return practitionerRepository.findAll()
                .stream()
                .map(PractitionerDTO::mapToDTO)
                .toList();
    }

    @Override
    public Practitioner getPractitioner( Long practitionerId ) {
        return practitionerRepository
                .findById( practitionerId )
                .orElseThrow(
                        () -> new PractitionerServiceException(
                                "Practitioner with ID " + practitionerId + " does not exist!" )
                );
    }
}
