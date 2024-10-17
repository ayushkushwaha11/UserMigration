package com.niyogin.UserMigration.controller;

import com.niyogin.UserMigration.model.NiyoCustomerCompany;
import com.niyogin.UserMigration.service.UserCustomerCompanyService;
import com.niyogin.UserMigration.service.impl.UserCustomerCompanyServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger _log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserCustomerCompanyService userCustomerCompanyService;

//    @GetMapping("/getNiyoCustomerById/{niyoCustomerCompanyId}")
//    public NiyoCustomerCompany getNiyoCustomerByCustomerId(@PathVariable long niyoCustomerCompanyId){
//        return userCustomerCompanyService.getNiyoCustomerByNiyoCustomerId(niyoCustomerCompanyId);
//    }

    @PostMapping("/add-customer-company-details")
    public ResponseEntity<String> addCustomerCompanyDetails(@RequestParam String companyName,@RequestParam String pincode,@RequestParam String industryType,@RequestParam String industrySubType,@RequestParam String mobileNo,@RequestParam String email,@RequestParam long niyoPartnerCompanyId){

        _log.info("Starting add-customer-company-detils Controller Class");
        String reponse = userCustomerCompanyService.addCustomerCompanyDetails(companyName,pincode, industryType, industrySubType,mobileNo,email,niyoPartnerCompanyId);
        _log.info(reponse);
        return ResponseEntity.ok(reponse);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<NiyoCustomerCompany> getCustomerById(@PathVariable("id") long niyoCustomerCompanyId) {
        try {
            NiyoCustomerCompany customer = userCustomerCompanyService.getNiyoCustomerByNiyoCustomerId(niyoCustomerCompanyId);
            return ResponseEntity.ok(customer);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // or handle with a custom error response
        } catch (Exception ex) {
            // Log the exception (use a logger instead of System.out)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // or handle with a custom error response
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<NiyoCustomerCompany>> getAllCustomerCompanies() {
        try {
            List<NiyoCustomerCompany> customerCompanies = userCustomerCompanyService.getAllNiyoCustomerCompany();

            if (customerCompanies.isEmpty()) {
                return ResponseEntity.noContent().build();  // HTTP 204 if no content is found
            }

            return ResponseEntity.ok(customerCompanies);
        } catch (Exception ex) {
            // Log the exception (you can replace with a logger)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // or handle with a custom error response
        }
    }

//    @DeleteMapping("/customers/{id}")
//    public ResponseEntity<Void> deleteCustomerById(@PathVariable("id") long niyoCustomerCompanyId) {
//        try {
//            boolean isDeleted = userCustomerCompanyService.deleteNiyoCustomerById(niyoCustomerCompanyId);
//
//            if (isDeleted) {
//                return ResponseEntity.noContent().build();  // HTTP 204 No Content
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // HTTP 404 if customer not found
//            }
//        } catch (Exception ex) {
//            // Log the exception (you can replace with a logger)
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // HTTP 500 for server errors
//        }
//    }


    // ====================================addCustomerUser==================================

    @PostMapping("/add-customer-user")
    public String addCustomerUser(@RequestParam String name,@RequestParam String mobileNo,@RequestParam String email,@RequestParam String cif,@RequestParam long niyoCustomerCompanyId,@RequestParam Boolean consent){

        _log.info("Starting add-customer-user Controller Class");
        String reponse = userCustomerCompanyService.addCustomerUser(name,mobileNo, email,cif,niyoCustomerCompanyId,consent);
        _log.info(reponse);
        return userCustomerCompanyService.addCustomerUser(name,mobileNo,email,cif,niyoCustomerCompanyId,consent);
    }

}
