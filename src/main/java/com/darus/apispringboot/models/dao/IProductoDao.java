package com.darus.apispringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.darus.apispringboot.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

}
