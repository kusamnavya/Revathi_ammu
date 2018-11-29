package com.hcl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

 
@RestController
@RequestMapping("/countries")
public class ReferenceDataController {
         
    @Autowired
    CountryRepository countryRepository;
 
 
    @RequestMapping("/{name}")
    public Country getCountry(@PathVariable String name) {
        Country result;
            result = countryRepository.findByName(name);
        return result;
    }
    @RequestMapping("/fetch/{id}")
    public List<Country> getCountry(@PathVariable int id) {

         List<Country>   result = countryRepository.findByIdGreaterThan(id);
			return result;
        
    }
    @RequestMapping("/save")
    public void saveCountry(Country c) {
       c.setId(34);
       c.setCode("hello");
       c.setName("chennai");
             countryRepository.save(c);
       
    }
    @RequestMapping("/update/{id}")
    public void updateCountry(@PathVariable int id) {
     Country c=countryRepository.findById(id);
     c.setCode("fff");
     c.setName("fffffff");
     countryRepository.save(c);
       
    }
    
    @RequestMapping("/delete/{id}")
    public String deleteCountry(@PathVariable int id) {
        countryRepository.delete(id);
		return "deleted successfully";
	      
    }
    @RequestMapping(value = "/listCountries")
	public ModelAndView getEmployees(@RequestParam("page") String page) {
		int pageid=Integer.parseInt(page);  
		int total=5;  
		if(pageid==1){}  
		else{  
		    pageid=pageid-1;  
		    pageid=pageid*total+1;  
		}  
		List<Country> list=countryRepository.listEmployees(pageid, total);
		return new ModelAndView("index","list",list);
	}
 
    @RequestMapping(method = RequestMethod.GET)
    List<Country> getCountries() {
        List<Country> result;
           result = new ArrayList();
        Iterable<Country> countryList = countryRepository.findAll();
        for (Country country : countryList) {
            result.add(country);
        }
        return result;
    
}
    
    /*@RequestMapping(value="/listPage",method = RequestMethod.GET)
    Page<Country> countryPageList(Pageable pageable){
    	return countryRepository.findAll(pageable);
    }*/
}