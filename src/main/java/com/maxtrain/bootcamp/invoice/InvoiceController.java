package com.maxtrain.bootcamp.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository iRepo;

    @GetMapping
    public ResponseEntity<Iterable<Invoice>> getInvoices() {
        var invoices = iRepo.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable int id){
        var invoice = iRepo.findById(id);
        if (invoice.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoice.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> postInvoice(@RequestBody Invoice invoice){
        if (invoice == null || invoice.getId() != 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var inv = iRepo.save(invoice);
        return new ResponseEntity<>(inv, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Invoice> putInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
        if (invoice == null || invoice.getId() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var inv = iRepo.findById(invoice.getId());
        if (inv.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var i = iRepo.save(invoice);
        return new ResponseEntity<>(i, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable int id) {
        var invoice = iRepo.findById(id);
        if (invoice.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iRepo.delete(invoice.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
