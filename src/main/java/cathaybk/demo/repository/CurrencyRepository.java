package cathaybk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cathaybk.demo.entity.Currency;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCurrencyCode(String code);
}