package com.pavelchak.DTO;

import com.pavelchak.controller.DirectorController;
import com.pavelchak.domain.Company;
import com.pavelchak.exceptions.NoSuchBookException;
import com.pavelchak.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CompanyDTO extends ResourceSupport {
    Company company;
    public CompanyDTO(Company company, Link selfLink) throws NoSuchBookException, NoSuchPersonException {
        this.company = company;
        add(selfLink);
        add(linkTo(methodOn(DirectorController.class).getDirectorsByCompanyID(company.getId())).withRel("persons"));
    }

    public Long getCompanyId() {
        return company.getId();
    }

    public String getCompanyName() {
        return company.getCompanyName();
    }

    public String getAuthor() {
        return company.getAuthor();
    }

    public String getPublisher() {
        return company.getPublisher();
    }

    public Integer getImprintYear() {
        return company.getImprintYear();
    }

    public Integer getAmount() {
        return company.getAmount();
    }
}
