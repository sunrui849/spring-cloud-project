package com.sr.cloud.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class PrivilegeValidateDTO {
    private String userId;
    private String userName;
    private List<PrivilegeDTO> privilegeDTOList;

}
