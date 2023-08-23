package model;

import lombok.*;

import java.sql.Timestamp;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ToBeIn {
    private int id;
    private Timestamp createdAt;
    private int idUser;
    private int idProject;
}
