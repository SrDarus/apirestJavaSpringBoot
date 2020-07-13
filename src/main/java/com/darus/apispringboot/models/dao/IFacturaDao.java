package com.darus.apispringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.darus.apispringboot.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
