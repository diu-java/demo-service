package com.diu.edu.demoservice.mapper;





import com.diu.edu.demoservice.dto.FacultyTypeDTO;
import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.entity.FacultyType;
import com.diu.edu.demoservice.entity.ItemInfo;

public class ItemInfoMapper {

    public static ItemInfoDTO convertToDTO(ItemInfo itemInfo){
        System.out.println("Step 5 Item info Map");
        ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
        itemInfoDTO.setId(itemInfo.getId());
        itemInfoDTO.setName(itemInfo.getName());
        itemInfoDTO.setCode(itemInfo.getCode());
        itemInfoDTO.setPrice(itemInfo.getPrice());
        itemInfoDTO.setActive(itemInfo.getActive());
        return itemInfoDTO;
    }

}
