package ru.tellurian.fin_lit_api.model.entity.user_budget;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.tellurian.fin_lit_api.model.entity.user.User;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "user_monthly_budget")
public class UserMonthlyBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_budget")
    private int id;

    @NotNull
    @Column(name = "monthly_income")
    private long monthlyIncome;

    @Column(name = "apartment_rent")
    private Long apartmentRent;

    @Column(name = "utility_fees")
    private Long utility;

    @Column(name = "internet")
    private Long internet;

    @Column(name = "health")
    private Long health;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        return "UserMonthlyBudget{" +
                "id=" + id +
                ", monthlyIncome=" + monthlyIncome +
                ", apartmentRent=" + apartmentRent +
                ", utility=" + utility +
                ", internet=" + internet +
                ", health=" + health +
                ", user=" + (user == null ? "" : user.getId()) +
                '}';
    }
}
