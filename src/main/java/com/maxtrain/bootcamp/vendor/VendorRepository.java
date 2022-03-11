package com.maxtrain.bootcamp.vendor;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VendorRepository extends CrudRepository<Vendor, Integer> {
    Optional<Vendor> findByCode(String code);
}
