package com.indocyber.rest.subject;

import com.indocyber.rest.subject.dto.SubjectInsertRequest;
import com.indocyber.rest.subject.dto.SubjectResponse;
import com.indocyber.rest.subject.dto.SubjectListResponse;
import com.indocyber.rest.subject.dto.SubjectUpdateRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public ResponseEntity<Page<SubjectListResponse>> getAll(@PageableDefault() Pageable pageable,
                                                            @RequestParam(required = false) String name,
                                                            @RequestParam(required = false) Boolean active) {
        Page<SubjectListResponse> pageData = subjectService.getAll(pageable, name, active);
        return new ResponseEntity<>(pageData, HttpStatusCode.valueOf(200));
    }

    //NOT RESTFUL API - just a test example for request headers.
    @GetMapping("header-test")
    public ResponseEntity<Map<String, String>> getHeaders(HttpServletResponse response,
                                                          @RequestHeader(value = "authentication", required = false) String auth,
                                                          @RequestHeader Map<String, String> headers) {
        System.out.println(auth);
        headers.forEach((key, val) -> System.out.printf("key:%s, vals:%s\n", key, val));
        response.addHeader("test", "test");

        return new ResponseEntity<>(headers, HttpStatusCode.valueOf(200));
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectResponse> getById(@PathVariable String id) {
        var dto = subjectService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<SubjectResponse> insert(@Valid @RequestBody SubjectInsertRequest dto) {
        return ResponseEntity.ok(subjectService.insert(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<SubjectResponse> update(@Valid @PathVariable String id,
                                                  @RequestBody SubjectUpdateRequest dto) {
        return ResponseEntity.ok(subjectService.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
