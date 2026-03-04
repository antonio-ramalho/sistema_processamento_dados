package org.project.infrastructure;

import org.project.entities.Company;
import org.project.entities.Expense;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CompanyTest {

    @Test
    public void testCalculoDeMedia() {
        Company com = new Company("340251", "80653975000158", "Brasileirada", "Testando", "pr");
        com.addDespesa(new Expense("Desp1", 2025, 500.00, 2025));
        com.addDespesa(new Expense("Des2", 2025, 300.00, 2025));

        double media = com.averageExpense();

        assertEquals(media, 400.00, "A média deve ser 400.00");
    }
}
