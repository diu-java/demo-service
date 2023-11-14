package com.diu.edu.demoservice.mapper;





import com.diu.edu.demoservice.dto.FacultyTypeDTO;
import com.diu.edu.demoservice.entity.FacultyType;

public class FacultyTypeMapper {

    public static FacultyTypeDTO convertToDTO(FacultyType facultyType){
        FacultyTypeDTO facultyTypeDTO = new FacultyTypeDTO();
        facultyTypeDTO.setId(facultyType.getId());
        facultyTypeDTO.setName(facultyType.getName());
        facultyTypeDTO.setCode(facultyType.getCode());
        facultyTypeDTO.setActive(facultyType.getActive());
        return facultyTypeDTO;
    }

}
