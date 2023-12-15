package dians.homework3.wines02.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="winery")
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private String photoUrl;
    private double xCordinate;
    private double yCordinate;
    private Region region;

    @ManyToMany(mappedBy = "wineries", fetch = FetchType.EAGER)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wine> wines;
}
