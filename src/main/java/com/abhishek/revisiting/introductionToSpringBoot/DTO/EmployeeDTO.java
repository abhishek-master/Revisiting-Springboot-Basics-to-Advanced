package com.abhishek.revisiting.introductionToSpringBoot.DTO;

import com.abhishek.revisiting.introductionToSpringBoot.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Integer id ;

    @NotNull
    @Digits(integer = 2, fraction = 0)
    @Max(value = 85, message = "Age cannot be greater than 85")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;

    @NotNull
    //@Pattern(regexp = "^(USER|ADMIN)$", message = "Role for an employee can be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

    @NotBlank(message = "Name needs a valid value")
    @Size(min = 1, max=20)
    private String name;

    @NotNull
    @Size(min = 5, max=20, message = "Address should fall in size between [5, 20] characters")
    private String address;

    @Email (message = "Email format incorrect")
    @NotNull
    private String email ;

    @NotNull
    @DecimalMax(value = "1000000.00", message = "Salary should be in the format XXXX.YY to XXXXXXX.YY")
    @DecimalMin(value = "1000.00", message = "Salary should be in the format XXXX.YY to XXXXXXX.YY")
    private Double salary;

    @PastOrPresent(message = "DOJ should not be in future")
    private LocalDate dateOfJoining ;

    @NotNull
    private Boolean isActive ;

}

