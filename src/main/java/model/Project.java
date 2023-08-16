package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Project {
    private int id;
    @NonNull
    private String title;
    private String description;
    private int idUser;
}
