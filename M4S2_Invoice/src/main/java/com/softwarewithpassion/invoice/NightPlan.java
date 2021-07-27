package com.softwarewithpassion.invoice;

import com.softwarewithpassion.readingvalues.ReadingValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;

public class NightPlan {
    private static final BigDecimal DAY_RATE = new BigDecimal("0.19288");
    private static final BigDecimal NIGHT_RATE = new BigDecimal("0.17531");
    private static final int DAY_START = 8;
    private static final int NIGHT_START = 23;
    private BigDecimal dayConsumption = ZERO;
    private BigDecimal nightConsumption = ZERO;

    public void addReadingValues(List<ReadingValue> readingValues) {
        readingValues.forEach(this::addReadingValue);
    }

    private void addReadingValue(ReadingValue readingValue) {
        if (readingValue.getTimestamp().getHour() >= DAY_START && readingValue.getTimestamp().getHour() < NIGHT_START) {
            this.dayConsumption = this.dayConsumption.add(readingValue.getValue());
        } else {
            this.nightConsumption = this.nightConsumption.add(readingValue.getValue());
        }
    }

    public List<InvoiceLine> toInvoiceLines() {
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        invoiceLines.add(new InvoiceLine("Day Rate", DAY_RATE, dayConsumption, DAY_RATE.multiply(dayConsumption)));
        invoiceLines.add(new InvoiceLine("Night Rate", NIGHT_RATE, nightConsumption, NIGHT_RATE.multiply(nightConsumption)));
        return invoiceLines;
    }
}
