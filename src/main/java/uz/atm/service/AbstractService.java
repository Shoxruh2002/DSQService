package uz.atm.service;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bekpulatov Shoxruh
 * createdAt 27/06/22
 */


public abstract class AbstractService<R extends JpaRepository> {

    protected R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

}
