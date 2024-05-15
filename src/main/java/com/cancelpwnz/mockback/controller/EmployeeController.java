package com.cancelpwnz.mockback.controller;

import com.cancelpwnz.mockback.error.BadRequest;
import com.cancelpwnz.mockback.model.Employee;
import com.cancelpwnz.mockback.model.request.EmployeeSortFiled;
import com.cancelpwnz.mockback.model.request.Filter;
import com.cancelpwnz.mockback.model.response.Page;
import com.cancelpwnz.mockback.services.EmployeeService;
import com.cancelpwnz.mockback.utils.EmployeeUtils;
import com.cancelpwnz.mockback.utils.PageUtils;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<Employee>> findAll(@ParameterObject Pageable p, Filter filter){
        return ResponseEntity.ok(service.findAll(filter, p));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "400")
    })
    @PostMapping
    @Transactional
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee e){
        EmployeeUtils.thorIfRecursive(e);
        Employee save = service.save(e);
        URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/"+save.getId())
                .buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(save);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<Employee> update(@Valid @RequestBody Employee e){
        if (e.getId() == null){
            throw new BadRequest("id cannot be null");
        }
        EmployeeUtils.thorIfRecursive(e);
        return ResponseEntity.ok(service.edit(e));
    }


    @GetMapping(value = "/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }


    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
