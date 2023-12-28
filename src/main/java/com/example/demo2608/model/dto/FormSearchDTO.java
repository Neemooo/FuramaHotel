package com.example.demo2608.model.dto;


import com.example.demo2608.annotation_custom.DayMustBiggerNow;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormSearchDTO {
    @NotBlank(message = "Should be written something")
    @Min(1)
    @Max(value = 4, message = "Maximum number of people is 4")
    String total_adults;

    @NotBlank(message = "Should be written something")
    @Min(0)
    @Max(value = 6, message = "Maximum number of children is 6")
    String total_children;

    @DayMustBiggerNow
    @NotBlank(message = "Should be written something")
    String checkin;

    @NotBlank(message = "Should be written something")
    String checkout;
}
