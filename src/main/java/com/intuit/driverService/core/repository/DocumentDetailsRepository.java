package com.intuit.driverService.core.repository;

import com.intuit.driverService.core.model.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentDetailsRepository extends JpaRepository<DocumentModel, Long> {

    List<DocumentModel> findAllByDriverId(Long driverId);

}
