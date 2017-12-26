package com.pavelchak.service;

import com.pavelchak.Repository.CompanyRepository;
import com.pavelchak.Repository.DirectorRepository;
import com.pavelchak.domain.Company;
import com.pavelchak.domain.Director;
import com.pavelchak.exceptions.ExistsPersonForBookException;
import com.pavelchak.exceptions.NoSuchBookException;
import com.pavelchak.exceptions.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DirectorRepository directorRepository;

    public Set<Company> getCompaniesByDirectorId(Long director_id) throws NoSuchPersonException {
//        Director director = personRepository.findOne(director_id);//1.5.9
        Director director = directorRepository.findById(director_id).get();//2.0.0.M7
        if (director == null) throw new NoSuchPersonException();
        return director.getCompanies();
    }

    public Company getCompany(Long company_id) throws NoSuchBookException {
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7
        if (company == null) throw new NoSuchBookException();
        return company;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public void updateCompany(Company uCompany, Long company_id) throws NoSuchBookException {
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7
        if (company == null) throw new NoSuchBookException();
        //update
        company.setCompanyName(uCompany.getCompanyName());
        company.setAuthor(uCompany.getAuthor());
        company.setPublisher(uCompany.getPublisher());
        company.setImprintYear(uCompany.getImprintYear());
        company.setAmount(uCompany.getAmount());
    }

    @Transactional
    public void deleteCompany(Long company_id) throws NoSuchBookException, ExistsPersonForBookException {
//        Company company = bookRepository.findOne(company_id);//1.5.9
        Company company = companyRepository.findById(company_id).get();//2.0.0.M7

        if (company == null) throw new NoSuchBookException();
        if (company.getDirectors().size() != 0) throw new ExistsPersonForBookException();
        companyRepository.delete(company);
    }
}
