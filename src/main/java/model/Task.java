package model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Task {
    private int id;
    @NonNull
    private String title;
    private String description;
    private Timestamp deadline;
    private int idProject;
    private int idStatus;
    private int idUser;

}
