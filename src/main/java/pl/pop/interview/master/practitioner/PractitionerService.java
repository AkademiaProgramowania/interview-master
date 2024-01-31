package pl.pop.interview.master.practitioner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
