package co.tanjona.man.jillo.model;

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
    private Integer id;
    @NonNull
    private String title;
    private String description;
    private Timestamp deadline;
    @NonNull
    private Integer idProject;
    @NonNull
    private Integer idStatus;
    @NonNull
    private Integer idUser;

}
