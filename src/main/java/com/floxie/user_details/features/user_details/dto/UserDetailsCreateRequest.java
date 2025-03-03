package com.floxie.user_details.features.user_details.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.commons.feature.user_details.enums.Gender;
import org.commons.feature.user_details.enums.WorkoutState;

public record UserDetailsCreateRequest(

    @DecimalMin(value = "5.0", message = "Weight must be at least 5 kilograms")
    @NotNull(message = "Weight cannot be null")
    Double kilograms,

    @NotNull(message = "Workout state is required")
    WorkoutState workoutState,

    @NotNull(message = "Gender is required")
    Gender gender,

    @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
    @NotNull(message = "Height cannot be null")
    Double height,

    @Min(value = 1, message = "Age must be at least 1")
    @NotNull(message = "Age cannot be null")
    Integer age
) {

}