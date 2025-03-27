package com.example.IPM.Coures.Project.admin;

import com.example.IPM.Coures.Project.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class AdminDTO extends UserDTO {

    private String accessKey;

}
