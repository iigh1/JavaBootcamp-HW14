package com.example.javabootcamphw14.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;

@AllArgsConstructor
@Data
public class Employee {

    @NotEmpty(message = "ID cannot be null")
    @Size(min=2, max =6, message = "ID length must be more than 2")
    private String id;

    @NotEmpty(message = "Name cannot be null")
    @Size(min=4, max=8, message = "name must be more than 4")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Min(value = 25,message = "Age must be greater than or equal to 25")
    private int age;

    @NotNull(message = "Role cannot be null")
    @Pattern(regexp = "supervisor|coordinator",message = "role must be either 'supervisor' or 'coordinator'")
    private String role;

    @AssertFalse()
    private boolean onLeave;

    @NotNull(message = "Employment year cannot be null")
    @Digits(integer = 4, fraction = 0, message = "Employment year must be a valid year")
    @Min(2022)
    @Max(2023)
    private int employmentYear;

    @NotNull(message = "Annual leave cannot be null")
    @Digits(integer = 2, fraction = 0, message = "Annual leave must be a number")
    private int annualLeave;
}
