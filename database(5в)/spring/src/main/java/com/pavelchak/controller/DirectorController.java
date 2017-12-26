package com.pavelchak.controller;

import com.pavelchak.DTO.DirectorDTO;
import com.pavelchak.domain.Director;
import com.pavelchak.exceptions.*;
import com.pavelchak.service.DirectorService;
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
public class DirectorController {
    @Autowired
    DirectorService directorService;

    @GetMapping(value = "/api/director/city/{city_id}")
    public ResponseEntity<List<DirectorDTO>> getDirectorsByCityID(@PathVariable Long city_id) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        List<Director> directorList = directorService.getDirectorByCityId(city_id);

        Link link = linkTo(methodOn(DirectorController.class).getAllDirectors()).withSelfRel();

        List<DirectorDTO> directorsDTO = new ArrayList<>();
        for (Director entity : directorList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            DirectorDTO dto = new DirectorDTO(entity, selfLink);
            directorsDTO.add(dto);
        }

        return new ResponseEntity<>(directorsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/director/{director_id}")
    public ResponseEntity<DirectorDTO> getDirector(@PathVariable Long director_id) throws NoSuchPersonException, NoSuchBookException {
        Director director = directorService.getDirector(director_id);
        Link link = linkTo(methodOn(DirectorController.class).getDirector(director_id)).withSelfRel();

        DirectorDTO directorDTO = new DirectorDTO(director, link);

       return new ResponseEntity<>(directorDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/director")
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() throws NoSuchPersonException, NoSuchBookException {
        List<Director> directorList = directorService.getAllDirectors();
        Link link = linkTo(methodOn(DirectorController.class).getAllDirectors()).withSelfRel();

        List<DirectorDTO> directorsDTO = new ArrayList<>();
        for (Director entity : directorList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            DirectorDTO dto = new DirectorDTO(entity, selfLink);
            directorsDTO.add(dto);
        }

        return new ResponseEntity<>(directorsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/director/copmany/{copmany_id}")
    public ResponseEntity<List<DirectorDTO>> getDirectorsByCompanyID(@PathVariable Long copmany_id) throws NoSuchBookException, NoSuchPersonException {
        Set<Director> directorList = directorService.getDirectorsByCompanyId(copmany_id);
        Link link = linkTo(methodOn(DirectorController.class).getAllDirectors()).withSelfRel();

        List<DirectorDTO> directorsDTO = new ArrayList<>();
        for (Director entity : directorList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            DirectorDTO dto = new DirectorDTO(entity, selfLink);
            directorsDTO.add(dto);
        }

        return new ResponseEntity<>(directorsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/director/city/{city_id}")
    public  ResponseEntity<DirectorDTO> addDirector(@RequestBody Director newDirector, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        directorService.createDirector(newDirector, city_id);
        Link link = linkTo(methodOn(DirectorController.class).getDirector(newDirector.getId())).withSelfRel();

        DirectorDTO directorDTO = new DirectorDTO(newDirector, link);

        return new ResponseEntity<>(directorDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/director/{director_id}/city/{city_id}")
    public  ResponseEntity<DirectorDTO> updateDirector(@RequestBody Director uDirector,
                                                     @PathVariable Long director_id, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        directorService.updateDirector(uDirector, director_id, city_id);
        Director director = directorService.getDirector(director_id);
        Link link = linkTo(methodOn(DirectorController.class).getDirector(director_id)).withSelfRel();

        DirectorDTO directorDTO = new DirectorDTO(director, link);

        return new ResponseEntity<>(directorDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/director/{director_id}")
    public  ResponseEntity deleteDirector(@PathVariable Long director_id) throws NoSuchPersonException, ExistsBooksForPersonException {
        directorService.deleteDirector(director_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/director/{director_id}/copmany/{copmany_id}")
    public  ResponseEntity<DirectorDTO> addCompanyForDirector(@PathVariable Long director_id, @PathVariable Long copmany_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
        directorService.addCompanyForDirector(director_id,copmany_id);
        Director director = directorService.getDirector(director_id);
        Link link = linkTo(methodOn(DirectorController.class).getDirector(director_id)).withSelfRel();

        DirectorDTO directorDTO = new DirectorDTO(director, link);

        return new ResponseEntity<>(directorDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/director/{director_id}/copmany/{copmany_id}")
    public  ResponseEntity<DirectorDTO> removeCompanyForDirector(@PathVariable Long director_id, @PathVariable Long copmany_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
        directorService.removeCompanyForDirector(director_id,copmany_id);
        Director director = directorService.getDirector(director_id);
        Link link = linkTo(methodOn(DirectorController.class).getDirector(director_id)).withSelfRel();

        DirectorDTO directorDTO = new DirectorDTO(director, link);

        return new ResponseEntity<>(directorDTO, HttpStatus.OK);
    }

}
