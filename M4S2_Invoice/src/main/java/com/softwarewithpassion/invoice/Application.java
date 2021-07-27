package com.softwarewithpassion.invoice;

import com.softwarewithpassion.readingvalues.ReadingValuesGenerator;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {
        LocalDateTime sinceClosed = LocalDateTime.parse("2020-01-01T00:00:00");
        LocalDateTime untilOpen = LocalDateTime.parse("2020-01-08T00:00:00");
        int meterSerialNumber = 4512;

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(new ReadingValuesGenerator());
        Invoice invoice = invoiceGenerator.generateInvoice(sinceClosed, untilOpen, meterSerialNumber);
        System.out.println("invoice = " + invoice);
    }
}
