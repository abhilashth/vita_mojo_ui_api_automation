package com.qa.automation.beans;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;

}
