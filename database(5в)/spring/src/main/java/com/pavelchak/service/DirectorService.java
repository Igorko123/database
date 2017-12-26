package com.pavelchak.service;

import com.pavelchak.Repository.CompanyRepository;
import com.pavelchak.Repository.CityRepository;
import com.pavelchak.Repository.DirectorRepository;
import com.pavelchak.domain.Company;
import com.pavelchak.domain.City;
import com.pavelchak.domain.Director;
import com.pavelchak.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Director> getDirectorByCityId(Long city_id) throws NoSuchCityException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city == null) throw new NoSuchCityException();
        return city.getDirectors();
    }

    public Director getDirector(Long director_id) throws NoSuchPersonException {
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
        return director;
    }

    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    public Set<Director> getDirectorsByCompanyId(Long company_id) throws NoSuchBookException {
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7
        if (company == null) throw new NoSuchBookException();
        return company.getDirectors();
    }

    @Transactional
    public void createDirector(Director director, Long city_id) throws NoSuchCityException {
        if (city_id > 0) {
//            City city = cityRepository.findOne(city_id);//1.5.9
            City city = cityRepository.findById(city_id).get();//2.0.0.M7
            if (city == null) throw new NoSuchCityException();
            director.setCity(city);
        }
        directorRepository.save(director);
    }

    @Transactional
    public void updateDirector(Director uDirector, Long director_id, Long city_id) throws NoSuchCityException, NoSuchPersonException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city_id > 0) {
            if (city == null) throw new NoSuchCityException();
        }
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
        //update
        director.setSurname(uDirector.getSurname());
        director.setName(uDirector.getName());
        director.setEmail(uDirector.getEmail());
        if (city_id > 0) director.setCity(city);
        else director.setCity(null);
        director.setStreet(uDirector.getStreet());
        director.setApartment(uDirector.getApartment());
        directorRepository.save(director);
    }

    @Transactional
    public void deleteDirector(Long director_id) throws NoSuchPersonException, ExistsBooksForPersonException {
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
        if (director.getCompanies().size() != 0) throw new ExistsBooksForPersonException();
        directorRepository.delete(director);
    }

    @Transactional
    public void addCompanyForDirector(Long director_id, Long company_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7
        if (company == null) throw new NoSuchBookException();
        if (director.getCompanies().contains(company) == true) throw new AlreadyExistsBookInPersonException();
        if (company.getAmount() <= company.getDirectors().size()) throw new BookAbsentException();
        director.getCompanies().add(company);
        directorRepository.save(director);
    }

    @Transactional
    public void removeCompanyForDirector(Long director_id, Long company_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7
        if (company == null) throw new NoSuchBookException();
        if (director.getCompanies().contains(company) == false) throw new PersonHasNotBookException();
        director.getCompanies().remove(company);
        directorRepository.save(director);
    }
}
