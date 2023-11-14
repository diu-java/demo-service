package com.diu.edu.demoservice.service;


import com.diu.edu.demoservice.dao.SaleSubDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.SaleSubDTO;
import com.diu.edu.demoservice.entity.SaleMain;
import com.diu.edu.demoservice.entity.SaleSub;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import com.diu.edu.demoservice.mapper.SaleSubMapper;
import com.diu.edu.demoservice.repository.SaleSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SaleSubService {


    @Autowired
    private SaleSubRepository saleSubRepository;

    public List<SaleSubDTO> findAll(Long sale_main_id) {
        List<SaleSubDTO> saleSubDTOS = null;
        List<SaleSub> saleSubs = saleSubRepository.findAllBySaleMain(SaleMain.builder().id(sale_main_id).build());
        if (!saleSubs.isEmpty()) {
            saleSubDTOS = saleSubs.stream()
                    .map(SaleSubMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return saleSubDTOS;
    }

    public SaleSubDTO findById(Long id) {
        SaleSub saleSub = saleSubRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return SaleSubMapper.convertToDTO(saleSub);
    }


    public ApiDTO save(Long id, SaleSubDAO saleSubDAO, String user_id) {
        Map<String,Object> data = saleSubRepository.spSaleSubSave(id,saleSubDAO.getSaleMainId(),saleSubDAO.getItemInfoId(),saleSubDAO.getPrice(),saleSubDAO.getQuantity(),user_id, "E");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        SaleSub saleSub = saleSubRepository.findById(Long.parseLong(data.get("out_id").toString()))
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));

        SaleSubDTO saleSubDTO = SaleSubMapper.convertToDTO(saleSub);
        ApiDTO<SaleSubDTO> responseDTO = ApiDTO
                .<SaleSubDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .data(saleSubDTO)
                .build();
        return responseDTO;
    }


    public ApiDTO delete(Long id,String user_id) {
        SaleSubDAO saleSubDAO = new SaleSubDAO();
        Map<String,Object> data = saleSubRepository.spSaleSubSave(id,saleSubDAO.getSaleMainId(),saleSubDAO.getItemInfoId(),saleSubDAO.getPrice(),saleSubDAO.getQuantity(),user_id, "D");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        ApiDTO<SaleSubDTO> responseDTO = ApiDTO
                .<SaleSubDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .build();
        return responseDTO;
    }
}
