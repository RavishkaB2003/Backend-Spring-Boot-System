package com.management.book.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookDTO {
    private String BookTitle;
    private long Isbn;
    private boolean Availability;
    private String Author;
    private String BookDescription;
}
