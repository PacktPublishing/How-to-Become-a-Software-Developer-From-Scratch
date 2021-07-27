package com.softwarewithpassion.invoice;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.softwarewithpassion.invoice.RecursiveComparisonConfigurations.comparingBigDecimalsWithComparator;
import static org.assertj.core.api.Assertions.assertThat;

class InvoiceGeneratorTest {
    private static final LocalDateTime SINCE_CLOSED = LocalDateTime.parse("2020-01-01T00:00:00");
    private static final LocalDateTime UNTIL_OPEN = LocalDateTime.parse("2020-01-08T00:00:00");
    private static final int METER_SERIAL_NUMBER = 4512;

    @Test
    void shouldGenerateInvoice() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(new ReadingValuesGeneratorStub());

        Invoice invoice = invoiceGenerator.generateInvoice(SINCE_CLOSED, UNTIL_OPEN, METER_SERIAL_NUMBER);

        assertThat(invoice).usingRecursiveComparison(comparingBigDecimalsWithComparator()).isEqualTo(expectedInvoice());
    }

    private Invoice expectedInvoice() {
        return new Invoice(List.of(
                new InvoiceLine("Day Rate", new BigDecimal("0.19288"), new BigDecimal(100), new BigDecimal("19.288")),
                new InvoiceLine("Night Rate", new BigDecimal("0.17531"), new BigDecimal(100), new BigDecimal("17.531"))
        ));
    }

    @Test
    void shouldGenerateReadingValuesBasedOnTheSameParameters() {
        ReadingValuesGeneratorSpy readingValuesGenerator = new ReadingValuesGeneratorSpy();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(readingValuesGenerator);

        invoiceGenerator.generateInvoice(SINCE_CLOSED, UNTIL_OPEN, METER_SERIAL_NUMBER);

        assertThat(readingValuesGenerator.getSinceClosed()).isEqualTo(SINCE_CLOSED);
        assertThat(readingValuesGenerator.getUntilOpen()).isEqualTo(UNTIL_OPEN);
        assertThat(readingValuesGenerator.getMeterSerialNumber()).isEqualTo(METER_SERIAL_NUMBER);
    }
}