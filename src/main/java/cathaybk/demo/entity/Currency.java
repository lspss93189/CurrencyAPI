package cathaybk.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "CURRENCY_NAME")
    private String currencyName;
}
