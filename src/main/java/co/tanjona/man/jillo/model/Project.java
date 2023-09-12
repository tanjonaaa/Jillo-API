package co.tanjona.man.jillo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Project {
    private Integer id;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private Integer idUser;
}
