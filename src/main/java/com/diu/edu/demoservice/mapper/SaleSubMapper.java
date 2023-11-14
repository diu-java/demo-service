package com.diu.edu.demoservice.mapper;





import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.dto.SaleMainDTO;
import com.diu.edu.demoservice.dto.SaleSubDTO;
import com.diu.edu.demoservice.entity.SaleSub;

public class SaleSubMapper {

    public static SaleSubDTO convertToDTO(SaleSub saleSub){
        SaleSubDTO saleSubDTO = new SaleSubDTO();
        saleSubDTO.setId(saleSub.getId());
        saleSubDTO.setSaleMain(SaleMainDTO.builder()
                .id(saleSub.getSaleMain().getId())
                .price(saleSub.getSaleMain().getPrice())
                .build());
        saleSubDTO.setItemInfo(ItemInfoDTO.builder()
                .id(saleSub.getItemInfo().getId())
                .code(saleSub.getItemInfo().getCode())
                .name(saleSub.getItemInfo().getName())
                .build());
        saleSubDTO.setPrice(saleSub.getPrice());
        saleSubDTO.setQuantity(saleSub.getQuantity());
        return saleSubDTO;
    }

}
