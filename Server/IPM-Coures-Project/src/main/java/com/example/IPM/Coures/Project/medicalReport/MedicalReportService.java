package com.example.IPM.Coures.Project.medicalReport;

import com.example.IPM.Coures.Project.general.BaseCrudService;
import com.example.IPM.Coures.Project.general.MyError;
import com.example.IPM.Coures.Project.resident.ResidentDTO;
import com.example.IPM.Coures.Project.resident.ResidentEntity;
import com.example.IPM.Coures.Project.resident.ResidentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalReportService extends BaseCrudService<MedicalReportEntity,MedicalReportDTO,String> {

    private final MedicalReportMapper mapper;
    private final MedicalReportRepository repository;
    @Autowired
    private ResidentMapper residentMapper;
    public MedicalReportService(MedicalReportRepository repository,MedicalReportMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<MedicalReportDTO> findByPatient(ResidentDTO dto) throws MyError {
        try {
            ResidentEntity entity = residentMapper.fromDTOToEntity(dto);
            return repository.findByPatient(entity).stream().map(mapper::fromEntityToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyError("не получилось найти");
        }
    }

    void deleteByPatient(ResidentDTO dto) throws MyError {
        try {
            repository.deleteByPatient(residentMapper.fromDTOToEntity(dto));
        } catch (Exception e) {
            throw new MyError("не получилось найти" + e.getMessage());
        }
    }

    public void delete(MedicalReportDTO dto) throws MyError {
        try {
            MedicalReportEntity entity = mapper.fromDTOToEntity(dto);
            repository.deleteById(entity.getId());
        } catch (Exception e) {
            throw new MyError("не получилось найти" + e.getMessage());
        }
    }
}
