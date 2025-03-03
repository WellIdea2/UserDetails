package com.floxie.user_details.features.user_details.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commons.feature.shared.entity.BaseEntity;
import org.commons.feature.user_details.enums.Gender;
import org.commons.feature.user_details.enums.WorkoutState;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails extends BaseEntity {

  @Column
  private Double kilograms;

  @Enumerated(EnumType.STRING)
  private WorkoutState workoutState;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column
  private Double height;

  @Column
  private Integer age;

  @Column
  private UUID userId;
}