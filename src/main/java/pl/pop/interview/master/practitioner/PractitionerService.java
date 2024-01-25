package pl.pop.interview.master.practitioner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;
    private final PractitionerMapper practitionerMapper;

    public Practitioner createNewPractitioner() {
        // create a new practitioner called only in creating new account
        Practitioner practitioner = new Practitioner();
        return practitionerRepository.save(practitioner);
    }

    public List<PractitionerDTO> listPractitioners() {
        return practitionerRepository.findAll()
                .stream()
                .map(practitioner -> practitionerMapper.mapEntityToDto(practitioner))
                .toList();
    }

    public Practitioner findById(Long id) {
        return practitionerRepository
                .findById(id)
                .orElseThrow(
                        () -> new PractitionerServiceException("No practitioner found"));

    }

}
