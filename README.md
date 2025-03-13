## Extensibility:

- The search functionality uses a predicate builder pattern, making it easy to add new filters
- The sorting mechanism is designed to accommodate new sort fields and orders
- The controller layer uses DTOs for clean request/response handling
- The repository layer abstracts data storage, making it easy to switch to a real database later

### Structure
```
swa-aham-flipkart-daily/
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/flipkart/daily/
│   │   │       ├── DemoRunner.java
│   │   │       ├── FlipkartDailyApplication.java
│   │   │       ├── controller/InventoryController.java
│   │   │       ├── dto/
│   │   │       │   ├── SearchRequest.java
│   │   │       │   └── SearchResponse.java
│   │   │       ├── exception/
│   │   │       │   ├── InvalidRequestException.java
│   │   │       │   ├── ItemNotFoundException.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       ├── model/
│   │   │       │   ├── InventoryItem.java
│   │   │       │   ├── Item.java
│   │   │       │   ├── SearchCriteria.java
│   │   │       │   └── SortCriteria.java
│   │   │       ├── repository/
│   │   │       │   ├── InventoryRepository.java
│   │   │       │   └── ItemRepository.java
│   │   │       ├── service/
│   │   │       │   ├── InventoryService.java
│   │   │       │   └── SearchService.java
│   │   │       └── util/Constants.java
│   │   └── resources/application.properties
│   └── test/
│       └── java/com/flipkart/daily/FlipkartDailyApplicationTests.java
└── .mvn/wrapper/maven-wrapper.properties
```
