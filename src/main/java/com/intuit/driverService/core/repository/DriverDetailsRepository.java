package com.intuit.driverService.core.repository;

import com.intuit.driverService.core.model.DriverDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface DriverDetailsRepository extends JpaRepository<DriverDetailsModel,Long> {

    DriverDetailsModel findByMobileNo(String mobileNo);

    List<DriverDetailsModel> findAllByStatus(String status);


    @Transactional
    @Modifying
    @Query(value = "UPDATE DriverDetailsModel d SET d.status=:status WHERE d.id =:driverId")
    int updateStatus(@Param("driverId") long driverId, @Param("status") String status);
}
