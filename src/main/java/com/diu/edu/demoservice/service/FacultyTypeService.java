package com.diu.edu.demoservice.service;


import com.diu.edu.demoservice.dao.FacultyTypeDAO;
import com.diu.edu.demoservice.dto.ApiDTO;
import com.diu.edu.demoservice.dto.FacultyTypeDTO;
import com.diu.edu.demoservice.entity.FacultyType;
import com.diu.edu.demoservice.exception.ServiceBusinessException;
import com.diu.edu.demoservice.exception.ServiceNotFoundException;
import com.diu.edu.demoservice.mapper.FacultyTypeMapper;
import com.diu.edu.demoservice.repository.FacultyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class FacultyTypeService {


    @Autowired
    private FacultyTypeRepository facultyTypeRepository;

    public List<FacultyTypeDTO> findAll() {
        List<FacultyTypeDTO> facultyTypeDTOS = null;
        List<FacultyType> facultyTypes = facultyTypeRepository.findAll();
        if (!facultyTypes.isEmpty()) {
            facultyTypeDTOS = facultyTypes.stream()
                    .map(FacultyTypeMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return facultyTypeDTOS;
    }

    public List<FacultyTypeDTO> findAllByActive() {
        List<FacultyTypeDTO> facultyTypeDTOS = null;
        List<FacultyType> facultyTypes = facultyTypeRepository.findAllByActive(true);
        if (!facultyTypes.isEmpty()) {
            facultyTypeDTOS = facultyTypes.stream()
                    .map(FacultyTypeMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new ServiceNotFoundException("Data not Found!!");
        }
        return facultyTypeDTOS;
    }


    public FacultyTypeDTO findById(Long id) {
        FacultyType facultyType = facultyTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return FacultyTypeMapper.convertToDTO(facultyType);
    }

    public FacultyTypeDTO findByIdAndActive(Long id) {
        FacultyType facultyType = facultyTypeRepository.findByIdAndActive(id,true)
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));
        return FacultyTypeMapper.convertToDTO(facultyType);
    }

    public ApiDTO save(Long id, FacultyTypeDAO facultyTypeDAO, String user_id) {
        Map<String,Object> data = facultyTypeRepository.spFacultyTypeSave(id,facultyTypeDAO.getCode(),facultyTypeDAO.getName(),facultyTypeDAO.getActive(),user_id, "E");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        FacultyType facultyType = facultyTypeRepository.findById(Long.parseLong(data.get("out_id").toString()))
                .orElseThrow(() -> new ServiceNotFoundException("Data not Found!!"));

        FacultyTypeDTO facultyTypeDTO = FacultyTypeMapper.convertToDTO(facultyType);
        ApiDTO<FacultyTypeDTO> responseDTO = ApiDTO
                .<FacultyTypeDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .data(facultyTypeDTO)
                .build();
        return responseDTO;
    }


    public ApiDTO delete(Long id,String user_id) {
        FacultyTypeDAO facultyTypeDAO = new FacultyTypeDAO();
        Map<String,Object> data = facultyTypeRepository.spFacultyTypeSave(id,facultyTypeDAO.getCode(),facultyTypeDAO.getName(),facultyTypeDAO.getActive(),user_id, "D");
        if(Integer.parseInt(data.get("out_message_code").toString()) > 0){
            throw new ServiceBusinessException(data.get("out_message_description").toString());
        }
        ApiDTO<FacultyTypeDTO> responseDTO = ApiDTO
                .<FacultyTypeDTO>builder()
                .status(true)
                .message(data.get("out_message_description").toString())
                .build();
        return responseDTO;
    }
}
