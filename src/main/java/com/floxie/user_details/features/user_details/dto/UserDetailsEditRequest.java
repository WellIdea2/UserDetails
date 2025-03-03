package com.floxie.user_details.features.user_details.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.commons.feature.user_details.enums.Gender;
import org.commons.feature.user_details.enums.WorkoutState;

public record UserDetailsEditRequest(

    @DecimalMin(value = "5.0", message = "Weight must be at least 5 kilograms")
    Double kilograms,

    WorkoutState workoutState,

    Gender gender,

    @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
    Double height,

    @Min(value = 1, message = "Age must be at least 1")
    Integer age
) {

}