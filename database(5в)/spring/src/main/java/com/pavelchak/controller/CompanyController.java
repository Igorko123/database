package com.pavelchak.controller;


import com.pavelchak.DTO.CompanyDTO;
import com.pavelchak.domain.Company;
import com.pavelchak.exceptions.ExistsPersonForBookException;
import com.pavelchak.exceptions.NoSuchBookException;
import com.pavelchak.exceptions.NoSuchPersonException;
import com.pavelchak.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/api/company/director/{director_id}")
    public ResponseEntity<List<CompanyDTO>> getCompaniesByDirectorID(@PathVariable Long director_id) throws NoSuchPersonException, NoSuchBookException {
        Set<Company> companyList = companyService.getCompaniesByDirectorId(director_id);
        Link link = linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel();

        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for (Company entity : companyList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CompanyDTO dto = new CompanyDTO(entity, selfLink);
            companiesDTO.add(dto);
        }

        return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/company/{company_id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long company_id) throws NoSuchBookException, NoSuchPersonException {
        Company company = companyService.getCompany(company_id);
        Link link = linkTo(methodOn(CompanyController.class).getCompany(company_id)).withSelfRel();

        CompanyDTO companyDTO = new CompanyDTO(company, link);

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/company")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() throws NoSuchBookException, NoSuchPersonException {
        List<Company> companyList = companyService.getAllCompanies();
        Link link = linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel();

        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for (Company entity : companyList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CompanyDTO dto = new CompanyDTO(entity, selfLink);
            companiesDTO.add(dto);
        }

        return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/company")
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody Company newCompany) throws NoSuchBookException, NoSuchPersonException {
        companyService.createCompany(newCompany);
        Link link = linkTo(methodOn(CompanyController.class).getCompany(newCompany.getId())).withSelfRel();

        CompanyDTO companyDTO = new CompanyDTO(newCompany, link);

        return new ResponseEntity<>(companyDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/company/{company_id}")
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody Company uCompany, @PathVariable Long company_id) throws NoSuchBookException, NoSuchPersonException {
        companyService.updateCompany(uCompany, company_id);
        Company company = companyService.getCompany(company_id);
        Link link = linkTo(methodOn(CompanyController.class).getCompany(company_id)).withSelfRel();

        CompanyDTO companyDTO = new CompanyDTO(company, link);

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/company/{company_id}")
    public  ResponseEntity deleteCompany(@PathVariable Long company_id) throws ExistsPersonForBookException, NoSuchBookException {
        companyService.deleteCompany(company_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
