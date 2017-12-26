package com.pavelchak.DTO;

import com.pavelchak.controller.CompanyController;
import com.pavelchak.domain.Director;
import com.pavelchak.exceptions.NoSuchBookException;
import com.pavelchak.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DirectorDTO extends ResourceSupport {
    Director director;
    public DirectorDTO(Director director, Link selfLink) throws NoSuchPersonException, NoSuchBookException {
        this.director = director;
        add(selfLink);
        add(linkTo(methodOn(CompanyController.class).getCompaniesByDirectorID(director.getId())).withRel("companies"));
    }

    public Long getDirectorId() {
        return director.getId();
    }

    public String getSurname() {
        return director.getSurname();
    }

    public String getName() {
        return director.getName();
    }

    public String getEmail() {
        return director.getEmail();
    }

    public String getCity() {
        if(director.getCity()==null) return "";
        return director.getCity().getCity();
    }

    public String getStreet() {
        return director.getStreet();
    }

    public String getApartment() {
        return director.getApartment();
    }
}
