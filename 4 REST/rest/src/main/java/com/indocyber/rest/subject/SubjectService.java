package com.indocyber.rest.subject;

import com.indocyber.rest.shared.exception.ResourceAlreadyExistException;
import com.indocyber.rest.shared.exception.ResourceNotFoundException;
import com.indocyber.rest.subject.dto.SubjectInsertRequest;
import com.indocyber.rest.subject.dto.SubjectResponse;
import com.indocyber.rest.subject.dto.SubjectListResponse;
import com.indocyber.rest.subject.dto.SubjectUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public Page<SubjectListResponse> getAll(Pageable pageable, String name, Boolean active) {
        return subjectRepository.findAll(name, active, pageable)
                .map(subjectMapper::toListDto);
    }

    public SubjectResponse getById(String id) {
        var subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No subject with that ID " + id));
        return subjectMapper.toDto(subject);
    }

    public SubjectResponse insert(SubjectInsertRequest dto) {
        if (subjectRepository.existsById(dto.getId())) {
            throw new ResourceAlreadyExistException("Subject with this ID already exist");
        }
        var saved = subjectRepository.save(subjectMapper.toEntity(dto));
        return subjectMapper.toDto(saved);
    }

    public SubjectResponse update(String id, SubjectUpdateRequest dto) {
        var subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No subject with that ID " + id));

        subjectMapper.updateEntityFromDto(subject, dto);
        var saved = subjectRepository.save(subject);

        return subjectMapper.toDto(saved);
    }

    public void deleteById(String id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject with ID %s doesn't exist".formatted(id));
        }
        subjectRepository.deleteById(id);
    }

}