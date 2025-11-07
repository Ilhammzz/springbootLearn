package com.indocyber.rest.subject;

import com.indocyber.rest.subject.dto.SubjectInsertRequest;
import com.indocyber.rest.subject.dto.SubjectListResponse;
import com.indocyber.rest.subject.dto.SubjectResponse;
import com.indocyber.rest.subject.dto.SubjectUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectListResponse toListDto(Subject subject);

    Subject toEntity(SubjectInsertRequest dto);

    SubjectResponse toDto(Subject saved);

    void updateEntityFromDto(@MappingTarget Subject subject, SubjectUpdateRequest dto);

}
