package BestGymEver;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Test
    public void testFindCustomer() {
        String customerDataShort = "./test/TestData/testDataShort.txt";
        String customerDataLog = "./test/TestData/testDataLog.txt";
        String customerIdentifier = "7703021234";

        FileManager fileManager = new FileManager(customerDataShort, customerDataLog);
        CustomerService customerService = new CustomerService(fileManager);
        Customer customer = customerService.findCustomer(customerIdentifier);

        assertNotNull(customer);
        assertEquals(customer.getPersonalNumber(), "7703021234");
        assertEquals(customer.getName(), "Alhambra Aromes");
    }

    @Test
    public void testCheckMembership() {
        FileManager fileManager = new FileManager("","");
        CustomerService customerService = new CustomerService(fileManager);

        LocalDate testDate = LocalDate.parse("2024-01-01");

        Customer customerHasValidMembership = new Customer("Alhambra Aromes", "7703021234", LocalDate.parse("2024-07-01"));
        assertEquals(MembershipStatus.CURRENT_MEMBER.getStatus(), customerService.checkMembership(customerHasValidMembership, testDate));

        Customer customerHasExpiredMembership = new Customer("Bear Belle", "8204021234", LocalDate.parse("2019-12-02"));
        assertEquals(MembershipStatus.FORMER_MEMBER.getStatus(),customerService.checkMembership(customerHasExpiredMembership, testDate));

        Customer customerHasExactlyOneYearOldMembership = new Customer("Kalle Alfredsson", "871112123", LocalDate.parse("2025-01-01"));
        assertEquals(MembershipStatus.CURRENT_MEMBER.getStatus(),customerService.checkMembership(customerHasExactlyOneYearOldMembership, testDate));

    }

}