package org.example.mytodo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Statement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Status {
    private int id;
    private String status;
}
