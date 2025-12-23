package com.mx.development.said.microservice.address.tool;

import com.mx.development.said.microservice.address.dto.RequestAddress;
import com.mx.development.said.microservice.address.dto.ResponseAddress;
import com.mx.development.said.microservice.address.entity.AddressEntity;

import java.time.LocalDateTime;

public class AddressTools {
    static public AddressEntity requestAddressToAddressEntity(RequestAddress address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(address.getStreet());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setPostalCode(address.getPostalCode());
        addressEntity.setStatus(address.getStatus());
        return addressEntity;
    }

    public static ResponseAddress createResponseAddress(AddressEntity addressEntityCreated) {
        ResponseAddress responseAddress = new ResponseAddress();
        responseAddress.setId(addressEntityCreated.getId());
        responseAddress.setStreet(addressEntityCreated.getStreet());
        responseAddress.setCountry(addressEntityCreated.getCountry());
        responseAddress.setCreatedAt(LocalDateTime.now().toString());
        responseAddress.setUpdatedAt(LocalDateTime.now().toString());
        responseAddress.setStatus(addressEntityCreated.getStatus());
        return responseAddress;
    }

}
