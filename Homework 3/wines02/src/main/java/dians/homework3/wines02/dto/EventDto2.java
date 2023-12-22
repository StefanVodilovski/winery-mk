package dians.homework3.wines02.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class EventDto2 {
    private String name;
    private byte[] photo;
}
