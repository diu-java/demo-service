package com.diu.edu.demoservice.repository;



import com.diu.edu.demoservice.entity.FacultyType;
import com.diu.edu.demoservice.entity.SaleMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SaleMainRepository extends JpaRepository<SaleMain,Long> {


    @Procedure(name = "sale_main_save")
    Map<String,Object> spSaleMainSave(Long id, LocalDate sale_date, String person, String user, String operation);


}
