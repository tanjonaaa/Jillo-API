package co.tanjona.man.jillo.model;

import lombok.*;

import java.sql.Timestamp;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Setter
public class ToBeAssignedTo {
    private Integer id;
    private Timestamp createdAt;
    @NonNull
    private Integer idTask;
    @NonNull
    private Integer idUser;
}
