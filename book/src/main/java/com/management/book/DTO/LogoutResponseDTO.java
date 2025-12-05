package com.management.book.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



//This DTO is used to transfer the token data and not allowing the service to expose the entity directly.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutResponseDTO {
    private String message;
    private Boolean success;


}
