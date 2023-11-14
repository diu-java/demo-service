package com.diu.edu.demoservice.service;


import com.diu.edu.demoservice.dao.SaleMainDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.SaleMainDTO;
import com.diu.edu.demoservice.entity.SaleMain;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import com.diu.edu.demoservice.mapper.SaleMainMapper;
import com.diu.edu.demoservice.repository.SaleMainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class SaleMainService {


    @Autowired
    private SaleMainRepository saleMainRepository;

    public List<SaleMainDTO> findAll() {
        List<SaleMainDTO> saleMainDTOS = null;
        List<SaleMain> saleMains = saleMainRepository.findAll();
        if (!saleMains.isEmpty()) {
            saleMainDTOS = saleMains.stream()
                    .map(SaleMainMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return saleMainDTOS;
    }



    public SaleMainDTO findById(Long id) {
        SaleMain saleMain = saleMainRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return SaleMainMapper.convertToDTO(saleMain);
    }


    public ApiDTO save(Long id, SaleMainDAO saleMainDAO, String user_id) {
        Map<String,Object> data = saleMainRepository.spSaleMainSave(id,saleMainDAO.getSaleDate(),saleMainDAO.getPerson(),user_id, "E");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        SaleMain saleMain = saleMainRepository.findById(Long.parseLong(data.get("out_id").toString()))
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));

        SaleMainDTO saleMainDTO = SaleMainMapper.convertToDTO(saleMain);
        ApiDTO<SaleMainDTO> responseDTO = ApiDTO
                .<SaleMainDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .data(saleMainDTO)
                .build();
        return responseDTO;
    }


    public ApiDTO delete(Long id,String user_id) {
        SaleMainDAO saleMainDAO = new SaleMainDAO();
        Map<String,Object> data = saleMainRepository.spSaleMainSave(id,saleMainDAO.getSaleDate(),saleMainDAO.getPerson(),user_id, "D");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        ApiDTO<SaleMainDTO> responseDTO = ApiDTO
                .<SaleMainDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .build();
        return responseDTO;
    }
}
