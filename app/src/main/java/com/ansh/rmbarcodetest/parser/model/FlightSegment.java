/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ansh.rmbarcodetest.parser.model;


import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.ansh.rmbarcodetest.parser.specs.Compartment;
import com.ansh.rmbarcodetest.parser.specs.Element;
import com.ansh.rmbarcodetest.parser.specs.InternationalDocumentVerification;
import parser.UTCCalendarFactory;

import static java.lang.String.format;
import static com.ansh.rmbarcodetest.parser.specs.Element.AIRLINE_NUMERIC_CODE;
import static com.ansh.rmbarcodetest.parser.specs.Element.CHECK_IN_SEQUENCE_NUMBER;
import static com.ansh.rmbarcodetest.parser.specs.Element.COMPARTMENT_CODE;
import static com.ansh.rmbarcodetest.parser.specs.Element.DATE_OF_FLIGHT;
import static com.ansh.rmbarcodetest.parser.specs.Element.FLIGHT_NUMBER;
import static com.ansh.rmbarcodetest.parser.specs.Element.FOR_AIRLINE_USE;
import static com.ansh.rmbarcodetest.parser.specs.Element.FREE_BAGGAGE_ALLOWANCE;
import static com.ansh.rmbarcodetest.parser.specs.Element.FREQUENT_FLYER_AIRLINE_DESIGNATOR;
import static com.ansh.rmbarcodetest.parser.specs.Element.FREQUENT_FLYER_NUMBER;
import static com.ansh.rmbarcodetest.parser.specs.Element.FROM_CITY_AIRPORT_CODE;
import static com.ansh.rmbarcodetest.parser.specs.Element.ID_AD_INDICATOR;
import static com.ansh.rmbarcodetest.parser.specs.Element.INTERNATIONAL_DOCUMENT_VERIFICATION;
import static com.ansh.rmbarcodetest.parser.specs.Element.MARKETING_CARRIER_DESIGNATOR;
import static com.ansh.rmbarcodetest.parser.specs.Element.OPERATING_CARRIER_DESIGNATOR;
import static com.ansh.rmbarcodetest.parser.specs.Element.OPERATING_CARRIER_PNR_CODE;
import static com.ansh.rmbarcodetest.parser.specs.Element.PASSENGER_STATUS;
import static com.ansh.rmbarcodetest.parser.specs.Element.SEAT_NUMBER;
import static com.ansh.rmbarcodetest.parser.specs.Element.SELECTEE_INDICATOR;
import static com.ansh.rmbarcodetest.parser.specs.Element.TO_CITY_AIRPORT_CODE;
import static com.ansh.rmbarcodetest.parser.specs.Occurrence.R;


public final class FlightSegment {
    private final Map<Element, CharSequence> elements;

    private FlightSegment(Map<Element, CharSequence> elements) {
        this.elements = Collections.unmodifiableMap(elements);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getPNR() {
        return getValue(OPERATING_CARRIER_PNR_CODE).trim();
    }

    public String getFromCity() {
        return getValue(FROM_CITY_AIRPORT_CODE);
    }

    public String getToCity() {
        return getValue(TO_CITY_AIRPORT_CODE);
    }

    public String getOperatingCarrierDesignator() {
        return getValue(OPERATING_CARRIER_DESIGNATOR).trim();
    }

    public String getFlightNumber() {
        return getValue(FLIGHT_NUMBER).trim();
    }

    public Integer getJulianDateOfFlight() {
        Integer dateOfFlight = null;
        try {
            dateOfFlight = Integer.parseInt(getValue(DATE_OF_FLIGHT).toString());
        } catch (NumberFormatException nfe) {
            // Ignore...
        }
        return dateOfFlight;
    }

    public Calendar getDateOfFlight() {
        Integer dateOfFlight = getJulianDateOfFlight();
        return dateOfFlight != null ? UTCCalendarFactory.getInstanceForDayOfYear(dateOfFlight) : null;
    }

    public Compartment getCompartmentCode() {
        return Compartment.parse(getValue(COMPARTMENT_CODE));
    }

    public String getSeatNumber() {
        return getValue(SEAT_NUMBER);
    }

    public String getCheckInSequenceNumber() {
        return getValue(CHECK_IN_SEQUENCE_NUMBER).trim();
    }

    public String getPassengerStatus() {
        return getValue(PASSENGER_STATUS);
    }

    public Integer getAirlineNumericCode() {
        Integer airlineNumericCode = null;
        try {
            airlineNumericCode = Integer.parseInt(getValue(AIRLINE_NUMERIC_CODE).toString());
        } catch (NumberFormatException nfe) {
            // Ignore...
        }
        return airlineNumericCode;
    }

    public String getSerialNumber() {
        return getValue(SEAT_NUMBER);
    }

    public String getSelecteeIndicator() {
        return getValue(SELECTEE_INDICATOR);
    }

    public InternationalDocumentVerification getInternationalDocumentVerification() {
        return InternationalDocumentVerification.parse(getValue(INTERNATIONAL_DOCUMENT_VERIFICATION));
    }

    public String getMarketingCarrierDesignator() {
        return getValue(MARKETING_CARRIER_DESIGNATOR).trim();
    }

    public String getFrequentFlyerDesignator() {
        return getValue(FREQUENT_FLYER_AIRLINE_DESIGNATOR).trim();
    }

    public String getFrequentFlyerNumber() {
        return getValue(FREQUENT_FLYER_NUMBER);
    }

    public String getIdAdIndicator() {
        return getValue(ID_AD_INDICATOR);
    }

    public String getFreeBaggageAllowance() {
        return getValue(FREE_BAGGAGE_ALLOWANCE);
    }

    public String getIndividualAirlineUse() {
        return getValue(FOR_AIRLINE_USE);
    }

    private String getValue(Element e) {
        CharSequence s = elements.get(e);
        return s != null ? s.toString() : null;
    }

    public Map<Element, CharSequence> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlightSegment)) {
            return false;
        }
        FlightSegment flightSegment = (FlightSegment) o;
        return Objects.equals(getElements(), flightSegment.getElements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(elements);
    }

    public final static class Builder {
        private final Map<Element, CharSequence> elements = new HashMap<>();

        private Builder() { /* ... */ }

        public Builder element(Element e, CharSequence s) {
            assertRepeatedOccurrence(e);
            elements.put(e, s);
            return this;
        }

        public FlightSegment build() {
            return new FlightSegment(elements);
        }

        private static void assertRepeatedOccurrence(Element e) {
            if (!e.getOccurrence().equals(R)) {
                throw new IllegalStateException(format("Element (%s) does not have REPEATED occurrence.", e.name()));
            }
        }
    }


    @Override
    public String toString() {
        return "FlightSegment{" +
                "elements=" + elements.values()+ "\n"+elements.keySet()+

        '}';
    }
}
