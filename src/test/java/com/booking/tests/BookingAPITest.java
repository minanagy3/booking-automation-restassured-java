package com.booking.tests;

import com.booking.api.HotelAPI;
import com.booking.api.ReservationAPI;
import com.booking.api.SearchAPI;
import com.booking.utils.ExcelDataProvider;
import com.booking.utils.DateHelper;
import io.restassured.response.Response;
import org.testng.annotations.*;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class BookingAPITest {
    private SearchAPI searchAPI;
    private HotelAPI hotelAPI;
    private ReservationAPI reservationAPI;
    private ExcelDataProvider.TestData testData;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private static final String TOLIP_HOTEL_ID = "12345"; // Replace with actual hotel ID

    @BeforeClass
    public void setUp() {
        searchAPI = new SearchAPI();
        hotelAPI = new HotelAPI();
        reservationAPI = new ReservationAPI();
    }

    @BeforeMethod
    public void loadTestData() {
        try {
            String excelPath = System.getProperty("user.dir") + 
                File.separator + "data" + File.separator + "test-data.xlsx";
            ExcelDataProvider dataProvider = new ExcelDataProvider(excelPath);
            testData = dataProvider.getTestData(1); // Row 1 (assuming row 0 is header)
            dataProvider.close();

            // Calculate dates if not provided in Excel
            if (testData.getCheckInDate() != null && !testData.getCheckInDate().isEmpty()) {
                checkInDate = ExcelDataProvider.parseDate(testData.getCheckInDate());
            } else {
                checkInDate = ExcelDataProvider.calculateCheckInDate();
            }

            if (testData.getCheckOutDate() != null && !testData.getCheckOutDate().isEmpty()) {
                checkOutDate = ExcelDataProvider.parseDate(testData.getCheckOutDate());
            } else {
                checkOutDate = ExcelDataProvider.calculateCheckOutDate(checkInDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Use default values if Excel fails
            checkInDate = ExcelDataProvider.calculateCheckInDate();
            checkOutDate = ExcelDataProvider.calculateCheckOutDate(checkInDate);
            testData = new ExcelDataProvider.TestData("Alexandria", "", "");
        }
    }

    @Test(priority = 1, description = "Search hotels API test")
    public void testSearchHotels() {
        String location = testData.getLocation() != null && !testData.getLocation().isEmpty() 
            ? testData.getLocation() : "Alexandria";
        
        Response response = searchAPI.searchHotels(location, checkInDate, checkOutDate);
        
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        
        // Verify response contains search results
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.length() > 0, "Response body should not be empty");
        
        System.out.println("Search API Response: " + response.getBody().prettyPrint());
    }

    @Test(priority = 2, description = "Get hotel details API test")
    public void testGetHotelDetails() {
        Response response = hotelAPI.getHotelById(TOLIP_HOTEL_ID);
        
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        
        // Verify hotel name in response
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("Tolip") || responseBody.contains("Alexandria"), 
            "Response should contain hotel information");
        
        System.out.println("Hotel Details Response: " + response.getBody().prettyPrint());
    }

    @Test(priority = 3, description = "Get hotel availability API test")
    public void testGetHotelAvailability() {
        String checkIn = DateHelper.formatDateForAPI(checkInDate);
        String checkOut = DateHelper.formatDateForAPI(checkOutDate);
        
        Response response = hotelAPI.getHotelAvailability(TOLIP_HOTEL_ID, checkIn, checkOut);
        
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        
        System.out.println("Hotel Availability Response: " + response.getBody().prettyPrint());
    }

    @Test(priority = 4, description = "Verify check-in and check-out dates in hotel availability")
    public void testVerifyDatesInHotelAvailability() {
        String checkIn = DateHelper.formatDateForAPI(checkInDate);
        String checkOut = DateHelper.formatDateForAPI(checkOutDate);
        
        Response response = hotelAPI.getHotelAvailability(TOLIP_HOTEL_ID, checkIn, checkOut);
        
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Verify dates are present in response
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains(checkIn) || responseBody.contains(checkInDate.toString()), 
            "Response should contain check-in date");
        assertTrue(responseBody.contains(checkOut) || responseBody.contains(checkOutDate.toString()), 
            "Response should contain check-out date");
    }

    @Test(priority = 5, description = "Verify hotel name in hotel details response")
    public void testVerifyHotelNameInResponse() {
        Response response = hotelAPI.getHotelById(TOLIP_HOTEL_ID);
        
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        
        // Verify hotel name
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.contains("Tolip Hotel Alexandria") || 
                   responseBody.contains("Tolip") || 
                   responseBody.contains("Alexandria"),
            "Response should contain 'Tolip Hotel Alexandria' or related information");
    }

    @Test(priority = 6, description = "Get hotel rooms API test")
    public void testGetHotelRooms() {
        String checkIn = DateHelper.formatDateForAPI(checkInDate);
        String checkOut = DateHelper.formatDateForAPI(checkOutDate);
        
        Response response = hotelAPI.getHotelRooms(TOLIP_HOTEL_ID, checkIn, checkOut);
        
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        
        System.out.println("Hotel Rooms Response: " + response.getBody().prettyPrint());
    }

    @Test(priority = 7, description = "Get hotel reviews API test")
    public void testGetHotelReviews() {
        Response response = hotelAPI.getHotelReviews(TOLIP_HOTEL_ID);
        
        // Assertions
        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        
        System.out.println("Hotel Reviews Response: " + response.getBody().prettyPrint());
    }

    @AfterMethod
    public void tearDownMethod() {
        // Optional: Log test completion
    }
}

