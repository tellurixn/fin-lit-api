package ru.tellurian.fin_lit_api.test

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.tellurian.fin_lit_api.model.entity.User
import ru.tellurian.fin_lit_api.model.entity.UserMonthlyBudget
import ru.tellurian.fin_lit_api.repository.user.UserBudgetRepository
import ru.tellurian.fin_lit_api.repository.user.UserRepository
import spock.lang.Shared
import spock.lang.Specification

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

@SpringBootTest
@Transactional
class FinLitApiTest extends Specification {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "tellme";

    @Shared
    private Connection connection;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBudgetRepository budgetRepository;

    @Shared
    private List<Integer> createdUsers = new ArrayList<>();
    @Shared
    private List<Integer> createdMonthlyBudget = new ArrayList<>();

    def setupSpec() {
        connection = DriverManager.getConnection(URL, USER, PASSWORD)
    }

    protected User createUser() {
        createUser("UNIT_TEST_USER" + System.currentTimeMillis(), "UNIT_TEST_PASSWORD", null)
    }

    protected User createUser(String login, String password, String comment) {
        PreparedStatement stmt = connection.prepareStatement(CREATE_USER_SQL);
        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.setString(3, comment);

        ResultSet rs =  stmt.executeQuery();
        Integer userId = null;
        while(rs.next()) {
            userId = rs.getInt("id_user")
        }
        rs.close();
        createdUsers.add(userId)
        return userRepository.findById(userId).orElse(null)
    }

    protected UserMonthlyBudget createMonthlyBudget(int idUser) {
        return createMonthlyBudget(idUser, 15000L, 3000L, 2400L, 312323L, 54555L)
    }

    protected UserMonthlyBudget createMonthlyBudget(int idUser,
                                                    Long monthlyBudget,
                                                    Long apartmentRent,
                                                    Long utilityFees,
                                                    Long internet,
                                                    Long health ) {
        PreparedStatement stmt = connection.prepareStatement(CREATE_USER_MONTHLY_BUDGET_SQL);
        stmt.setInt(1, idUser);
        stmt.setLong(2, monthlyBudget);
        stmt.setLong(3, apartmentRent);
        stmt.setLong(4, utilityFees);
        stmt.setLong(5, internet);
        stmt.setLong(6, health);

        ResultSet rs =  stmt.executeQuery();
        Integer idUserBudget = null;
        while(rs.next()) {
            idUserBudget = rs.getInt("id_user_budget")
        }
        rs.close();
        createdMonthlyBudget.add(idUserBudget)
        return budgetRepository.findById(idUserBudget).orElse(null)
    }

    def cleanupSpec() {
        for (int id : createdMonthlyBudget) {
            PreparedStatement stmt = connection.prepareStatement(DELETE_USER_BUDGET_SQL);
            stmt.setInt(1, id)
            stmt.execute()
        }

        for (int id : createdUsers) {
            PreparedStatement stmt = connection.prepareStatement(DELETE_USER_SQL);
            stmt.setInt(1, id)
            stmt.execute()
        }
    }

    private final static String CREATE_USER_SQL = "" +
            "insert into public.user(login, password, comment) " +
            "values (?, ?, ?) " +
            "returning id_user " +
            ""

    private final static String DELETE_USER_SQL = "" +
            "delete from public.user " +
            "where id_user = ? " +
            ""

    private final static String CREATE_USER_MONTHLY_BUDGET_SQL = "" +
            "insert into public.user_monthly_budget(id_user, monthly_income, apartment_rent, utility_fees, internet, health) " +
            "values (?, ?, ?, ?, ?, ?) " +
            "returning id_user_budget " +
            "";

    private final static String DELETE_USER_BUDGET_SQL = "" +
            "delete from public.user_monthly_budget " +
            "where id_user_budget = ? " +
            ""
}
