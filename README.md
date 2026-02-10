A. This is a Spring Boot REST API for managing recycling centers and waste.
The project demonstrates working with REST, a database, validation, exceptions, and design patterns. B. Get all centers
GET /api/recycling-centers
Get a center by ID
GET /api/recycling-centers/{id}
Create a center
POST /api/recycling-centers
JSON example:
{
"id": 3,
"name": "EcoCity",
"capacity": 800,
"address": {
"street": "Main street",
"city": "Berlin",
"postalCode": "10115"
}
}
Update a center
PUT /api/recycling-centers/{id}
Delete a center
DELETE /api/recycling-centers/{id}
Get a center's waste
GET /api/recycling-centers/{id}/waste-items
Get the total weight of a center's waste
GET /api/recycling-centers/{id}/total-weight
Waste Items
Get all waste
GET /api/waste-items
Get waste by id
GET /api/waste-items/{id}
Create waste
POST /api/waste-items
Example JSON:
{
"id": 10,
"name": "Plastic bottles",
"wasteType": "plastic",
"weight": 12.5,
"recyclable": true,
"centerId": 1
}
C. Singleton is used for:
DatabaseConfig — a single connection to the database.
Ensures that the object is created only once.
Factory
WasteItemFactory creates different types of waste:
plastic
paper
electronic
The controller and service work with the base WasteItem class without knowing the specific implementation. This means that the client code works with a common interface without knowing the exact class of the object being created. Builder
RecyclingCenterBuilder is used to conveniently and safely create a recycling center. This simplifies the creation of complex objects with a large number of parameters.
D.
Reuse-Release Equivalence Principle (REP)
Only those classes that will be reused should be included in a single component.
factory/package contains reusable factory and builder patterns
exception/package contains reusable exception handling components
model/interfaces/ contains reusable interfaces.
Common Closure Principle (CCP)
A single component should include classes that change for the same reason and at the same time.
model/package groups all entity classes and their interfaces
service/package groups all business logic services
repository/package groups all data access components
Common Reuse Principle (CRP)
Don't force the user of a component to depend on something they don't need. If classes aren't used together, they shouldn't be included in a single component. DTOs are separated from entities to avoid unnecessary dependencies
Interfaces are in separate packages from implementations
E. The project uses:
Inheritance (WasteItem → PlasticWaste, PaperWaste, ElectronicWaste)
Polymorphism (different recycling cost calculations)
Encapsulation (access via services)
SRP - each class performs one task
OCP - easily add a new waste type
LCP - All waste item subclasses (PlasticWaste, ElectronicWaste, PaperWaste) can substitute WasteItem.
ICP - Chargeable interface only for cost calculation
Validatable interface only for validation
Searchable interface only for search functionality
Each service has its own specific interface
DIP - dependencies via interfaces.
H. Execution Instructions: Database Setup: Create database waste_management. Run the provided SQL schema file. Driver: Compile & Run. Click on run.
