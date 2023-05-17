package com.example.tacocloud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestObject implements Serializable {
  private static final long serialVersionUID = 1L;
  private String studentId;
  private String name;
  private String rollNumber;
}
