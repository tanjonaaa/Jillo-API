package co.tanjona.man.jillo.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Status {
    private Integer id;
    @NonNull
    private String name;
}
