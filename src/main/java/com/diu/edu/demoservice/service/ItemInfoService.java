package com.diu.edu.demoservice.service;


import com.diu.edu.demoservice.dao.ItemInfoDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.ItemInfoDTO;
import com.diu.edu.demoservice.entity.ItemInfo;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import com.diu.edu.demoservice.mapper.ItemInfoMapper;
import com.diu.edu.demoservice.repository.ItemInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemInfoService {


    @Autowired
    private ItemInfoRepository itemInfoRepository;

    public List<ItemInfoDTO> findAll() {
        List<ItemInfoDTO> itemInfoDTOS = null;
        List<ItemInfo> itemInfos = itemInfoRepository.findAll();
        if (!itemInfos.isEmpty()) {
            itemInfoDTOS = itemInfos.stream()
                    .map(ItemInfoMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return itemInfoDTOS;
    }

    public List<ItemInfoDTO> findAllByActive() {
        List<ItemInfoDTO> itemInfoDTOS = null;
        List<ItemInfo> itemInfos = itemInfoRepository.findAllByActive(true);
        if (!itemInfos.isEmpty()) {
            itemInfoDTOS = itemInfos.stream()
                    .map(ItemInfoMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return itemInfoDTOS;
    }


    public ItemInfoDTO findById(Long id) {
        ItemInfo itemInfo = itemInfoRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return ItemInfoMapper.convertToDTO(itemInfo);
    }

    public ItemInfoDTO findByIdAndActive(Long id) {
        ItemInfo itemInfo = itemInfoRepository.findByIdAndActive(id,true)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return ItemInfoMapper.convertToDTO(itemInfo);
    }

    public ApiDTO save(Long id, ItemInfoDAO itemInfoDAO, String user_id) {

        System.out.println("Step 3 Item info Service");
        Map<String,Object> data = itemInfoRepository.spItemInfoSave(id,itemInfoDAO.getCode(),itemInfoDAO.getName(),itemInfoDAO.getPrice(),itemInfoDAO.getActive(),user_id, "E");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        System.out.println("Step 4 Item info Repository");
        ItemInfo itemInfo = itemInfoRepository.findById(Long.parseLong(data.get("out_id").toString()))
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));

        ItemInfoDTO itemInfoDTO = ItemInfoMapper.convertToDTO(itemInfo);
        ApiDTO<ItemInfoDTO> responseDTO = ApiDTO
                .<ItemInfoDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .data(itemInfoDTO)
                .build();
        return responseDTO;
    }


    public ApiDTO delete(Long id,String user_id) {
        ItemInfoDAO itemInfoDAO = new ItemInfoDAO();
        Map<String,Object> data = itemInfoRepository.spItemInfoSave(id,itemInfoDAO.getCode(),itemInfoDAO.getName(),itemInfoDAO.getPrice(),itemInfoDAO.getActive(),user_id, "D");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        ApiDTO<ItemInfoDTO> responseDTO = ApiDTO
                .<ItemInfoDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .build();
        return responseDTO;
    }
}
