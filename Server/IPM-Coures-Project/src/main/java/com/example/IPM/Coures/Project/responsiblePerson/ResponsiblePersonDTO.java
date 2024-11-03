package com.example.IPM.Coures.Project.responsiblePerson;

import com.example.IPM.Coures.Project.general.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsiblePersonDTO implements DTO {

    private String FIO;
    private String phone;
    private String mail;
    private List<String> positions = new ArrayList<>();

}
