package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends UserDTO {

    private String accessKey;

}
