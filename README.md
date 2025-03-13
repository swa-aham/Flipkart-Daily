## Extensibility:

- The search functionality uses a predicate builder pattern, making it easy to add new filters
- The sorting mechanism is designed to accommodate new sort fields and orders
- The controller layer uses DTOs for clean request/response handling
- The repository layer abstracts data storage, making it easy to switch to a real database later
