package dians.homework3.wines02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.transaction.Transactional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class EventDto2 {
    private String name;
    private String photoUrl;
    private String description;
}
