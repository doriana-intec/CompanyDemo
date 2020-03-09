package be.intecbrussel.service.service_interfaces;

import be.intecbrussel.custom_exception.CustomException;

import java.util.List;

public interface Service<T> {

    void create(T object) throws CustomException;

    T readById(int id) throws CustomException;

    void update(T object) throws CustomException;

    void deleteById(int id) throws CustomException;

    List<T> getList() throws CustomException;
}
