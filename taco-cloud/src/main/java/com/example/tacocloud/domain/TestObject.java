package com.example.tacocloud.domain;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestObject implements Serializable {
  private static final long serialVersionUID = 1L;
  private String studentId;
  private String name;
  private String rollNumber;
}
