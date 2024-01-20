# Booking.com API Automation Tests - REST Assured Java

This project contains automated API tests for Booking.com using REST Assured, TestNG, Maven, and Java.

## 📋 Requirements

- Java 11 or higher
- Maven 3.6 or higher
- Internet connection for API calls

## 🚀 Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/booking-automation-restassured-java.git
   cd booking-automation-restassured-java
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

3. **Create Excel test data file:**
   ```bash
   mvn exec:java -Dexec.mainClass="com.booking.utils.CreateExcelData" -Dexec.classpathScope=test
   ```

   Or manually create `data/test-data.xlsx` with the following structure:
   - Column A: Location (e.g., "Alexandria")
   - Column B: CheckInDate (format: DD/MM/YYYY)
   - Column C: CheckOutDate (format: DD/MM/YYYY)

## 📁 Project Structure

```
booking-automation-restassured-java/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── booking/
│       │           ├── api/              # API client classes
│       │           │   ├── BaseAPI.java
│       │           │   ├── SearchAPI.java
│       │           │   ├── HotelAPI.java
│       │           │   └── ReservationAPI.java
│       │           ├── tests/             # Test classes
│       │           │   └── BookingAPITest.java
│       │           └── utils/             # Utility classes
│       │               ├── ExcelDataProvider.java
│       │               ├── DateHelper.java
│       │               └── CreateExcelData.java
│       └── resources/
│           └── testng.xml
├── data/                                 # Test data files
│   └── test-data.xlsx
├── pom.xml
├── testng.xml
└── README.md
```

## 🧪 Test Cases

The project includes the following API test cases:

1. **Search hotels API** - Tests hotel search endpoint with location and dates
2. **Get hotel details API** - Tests hotel details endpoint
3. **Get hotel availability API** - Tests hotel availability endpoint
4. **Verify dates in hotel availability** - Asserts that check-in and check-out dates are correctly returned
5. **Verify hotel name in response** - Asserts that hotel name is present in API response
6. **Get hotel rooms API** - Tests hotel rooms endpoint
7. **Get hotel reviews API** - Tests hotel reviews endpoint

## 📊 Test Data

Test data is stored in `data/test-data.xlsx` with the following columns:
- **Location**: Search location (e.g., "Alexandria")
- **CheckInDate**: Check-in date (format: DD/MM/YYYY)
- **CheckOutDate**: Check-out date (format: DD/MM/YYYY)

If dates are not provided in Excel, the system will automatically calculate:
- Check-in: 1 week from today
- Check-out: 4 days after check-in

## 🏃 Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=BookingAPITest
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run in IDE:
- Right-click on `testng.xml` → Run As → TestNG Suite
- Or right-click on `BookingAPITest.java` → Run As → TestNG Test

## ⚙️ Configuration

### API Base URL

Edit `BaseAPI.java` to change the base URI:
```java
protected static final String BASE_URI = "https://www.booking.com";
protected static final String API_BASE_PATH = "/api";
```

### Hotel ID

Edit `BookingAPITest.java` to use actual hotel ID:
```java
private static final String TOLIP_HOTEL_ID = "12345"; // Replace with actual hotel ID
```

## 🎯 Features

- ✅ REST Assured for API testing
- ✅ Page Object Model equivalent (API client classes)
- ✅ Excel data provider using Apache POI
- ✅ TestNG for test execution and reporting
- ✅ JSON response validation
- ✅ Automatic date calculation
- ✅ Comprehensive test coverage
- ✅ Maven project structure

## 📝 Notes

- **Important**: This project uses example API endpoints. Booking.com's actual API endpoints may require authentication and may have different structures.
- You may need to:
  - Update API endpoints to match actual Booking.com API
  - Add authentication headers if required
  - Update request/response models based on actual API documentation
  - Replace hotel IDs with actual values

## 📦 Dependencies

- **REST Assured** 5.3.2
- **JSON Schema Validator** 5.3.2
- **Jackson** 2.15.2 (for JSON processing)
- **TestNG** 7.8.0
- **Apache POI** 5.2.4 (for Excel)

## 🔧 API Testing Best Practices

1. **Request/Response Validation**: All tests validate status codes and response bodies
2. **Data-Driven Testing**: Uses Excel for test data management
3. **Reusable API Clients**: API methods are organized in separate classes
4. **Error Handling**: Proper exception handling and assertions
5. **Logging**: Response bodies are logged for debugging

## 📄 License

ISC

## 👤 Author

Junior QA Engineer

