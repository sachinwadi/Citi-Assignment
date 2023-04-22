package com.citi.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "VALID_INPUT_STORE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyEntity {

    @Id
    @Column(name="INPUT")
    private String value;
}
