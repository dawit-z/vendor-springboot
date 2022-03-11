package com.maxtrain.bootcamp.invoiceline;

import com.maxtrain.bootcamp.vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoicelines")
public class InvoicelineController {

    @Autowired
    private InvoicelineRepository invRepo;

    @GetMapping
    public ResponseEntity<Iterable<Invoiceline>> getInvoicelines(){
        var lines = invRepo.findAll();
        return new ResponseEntity<>(lines, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoiceline> getInvoiceline(@PathVariable int id){
        var line = invRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(line.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> postInvoiceline(@RequestBody Invoiceline invoiceline){
        if (invoiceline == null || invoiceline.getId() != 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var line = invRepo.save(invoiceline);
        return new ResponseEntity<>(line, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoiceline> putInvoiceline(@PathVariable int id, @RequestBody Invoiceline invoiceline) {
        if (invoiceline == null || invoiceline.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var inv = invRepo.findById(invoiceline.getId());
        if (inv.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var result = invRepo.save(invoiceline);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteInvoiceline(@PathVariable int id) {
        var line = invRepo.findById(id);
        if (line.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invRepo.delete(line.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
