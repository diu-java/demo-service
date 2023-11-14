package com.diu.edu.demoservice.mapper;





import com.diu.edu.demoservice.dto.SaleMainDTO;
import com.diu.edu.demoservice.entity.SaleMain;

public class SaleMainMapper {

    public static SaleMainDTO convertToDTO(SaleMain saleMain){
        SaleMainDTO saleMainDTO = new SaleMainDTO();
        saleMainDTO.setId(saleMain.getId());
        saleMainDTO.setSaleDate(saleMain.getSaleDate());
        saleMainDTO.setPrice(saleMain.getPrice());
        saleMainDTO.setPerson(saleMain.getPerson());
        return saleMainDTO;
    }

}
