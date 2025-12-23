package com.mx.development.said.microservice.address.service;

import com.mx.development.said.microservice.address.dto.RequestAddress;
import com.mx.development.said.microservice.address.dto.ResponseAddress;
import com.mx.development.said.microservice.address.entity.AddressEntity;
import com.mx.development.said.microservice.address.exception.AddressNotFoundException;
import com.mx.development.said.microservice.address.repository.AddressRepository;
import com.mx.development.said.microservice.address.tool.AddressTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        log.info("AddressService instance created");
        this.addressRepository = addressRepository;
    }

    public List<ResponseAddress> findAll() {
        log.info("Listing address...");
        List<AddressEntity> addressEntityList = addressRepository.findAll();
        List<ResponseAddress> responseAddressList = addressEntityList
                .stream()
                .map(AddressTools::createResponseAddress).toList();
        return responseAddressList;
    }

    public ResponseAddress createAddress(RequestAddress address){
        AddressEntity addressEntity = AddressTools.requestAddressToAddressEntity(address);
        AddressEntity addressCreated = addressRepository.save(addressEntity);
        ResponseAddress responseAddress = AddressTools.createResponseAddress(addressCreated);
        log.info("Address created: {}", responseAddress);
        return responseAddress;
    }

    public ResponseAddress getAddressById(Long addressId) {
        Optional<AddressEntity> optionalAddressEntity = addressRepository.findById(addressId);

        if(optionalAddressEntity.isEmpty()){
            throw new AddressNotFoundException(addressId);
        }

        return AddressTools.createResponseAddress(optionalAddressEntity.get());

    }

    public List<ResponseAddress> findByStatus(String status) {
        log.info("clase: {}", AddressService.class.getClass().getSimpleName());
        List<AddressEntity> addressEntityList = addressRepository.findByStatus(status);
        log.info("Encontró {} direcciones con el estatus: {}", addressEntityList.size(), status);

        return addressEntityList
                .stream()
                .map(AddressTools::createResponseAddress)
                .toList();
    }
}