package com.example.CrudApp.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column
    @NotBlank
    @Size(max = 255 , message = "maximum 255")
    private String description;
    @Column
    @JsonFormat(pattern = "yyyy-mm-dd")
    @NotNull(message="Date ne peut être null")
    private Date creationDate;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Date ne peut être null")
    private Date dueDate;

}
