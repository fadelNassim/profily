# profily

## Project description 
In this Android app, the user can scroll through and get details from an infinite amount of random user profiles, Profily can also be used offline.

## Demo
| First Use Network ON  | First use Network OFF | Second use Network OFF |
| ------------- | ------------- | ------------- |
|<video height="400" width="200"  src="https://github.com/user-attachments/assets/8de4f0fe-04be-4b1e-aaa7-41e53fee3126"/>|<video height="400" width="200" src="https://github.com/user-attachments/assets/ccb206f3-2ad9-4ad6-91c4-b15366e656e4"/>|<video height="400" width="200" src="https://github.com/user-attachments/assets/5233f68f-cebd-4969-aefe-5bc327626256"/>|

---

## Getting Started

To get started with this project:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/fadelNassim/profily.git
   ```

2. **Open the project in Android Studio:**
   Simply open the project directory in Android Studio, and it is ready to go.

## Project Architecture

### 1. Layers

- **UI Layer:** 
  Handles user interface and interactions. Displays data and captures user input.

- **Presentation Layer:**
  Bridges the UI and Domain layers. Manages UI-related data and updates the UI based on data changes.

- **Domain Layer:**
  Contains business logic and rules. Defines application behavior and handles complex operations.

- **Data Layer:**
  Manages data operations. Fetches data from remote or local sources and provides it to the Domain layer. Abstracts data sources to provide a unified API for data access.

### 2. Structural Design Pattern: MVVM

- **Model:**
  Represents the application's data and business logic. In this project, the model is represented by the `Profile` class.

- **View:**
  Responsible for displaying data and handling user interactions. In this project, views are represented by Compose components like `ProfilesScreen`. They use `StateFlow` and `PagingData` to observe state changes and update the UI.

- **ViewModel:**
  Acts as a bridge between the model and view, exposing data in an observable manner. The `ProfilesViewModel` class uses `Flow` for asynchronous operations and `MutableStateFlow` and `PagingData` for managing and updating the list of profiles.

## Library Choices

- **[Hilt](https://dagger.dev/hilt/):** 
  Dependency injection management. Simplifies dependency injection with predefined containers.

- **[Retrofit](https://square.github.io/retrofit/):** 
  Networking library that converts JSON responses into Kotlin objects. Used to interact with the [API](https://randomuser.me/api/1.3/?seed=lydia&results=20&page=1) for retrieving profiles.

- **[Room](https://developer.android.com/training/data-storage/room):** 
  Local SQLite database storage. Works with `RemoteMediator` for optimized caching.

- **[RemoteMediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator):** 
  Synchronizes data between a local database and a remote data source. Handles data pagination efficiently.

- **[Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview):** 
  Manages large datasets by loading and displaying small portions at a time. Works with `RemoteMediator` for smooth performance.

- **[Compose](https://developer.android.com/jetpack/compose):** 
  Declarative UI toolkit for building the user interface. Offers concise and readable code.

- **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** 
  Manages asynchronous operations in a sequential manner.

- **[Flow and MutableStateFlow](https://kotlinlang.org/docs/flow.html):** 
  Manages asynchronous data streams and UI state reactively.

- **[Coil](https://coil-kt.github.io/coil/):** 
  Lightweight image loading library for fast and easy image handling.

- **[MockK](https://mockk.io/):** 
  Creates mocks for unit tests, enabling simulation of dependencies.

- **[Gson](https://github.com/google/gson):** 
  Converts Java objects to JSON and vice versa. Used with Retrofit for JSON parsing.

---

# Possible Improvements
- Although not necessary in this excercise, It is a good practice to have a contract(interface) injected as a dependency rather than a direct implementation, allowing a smoother transition if one of the implementation of a dependency has to change in the futur.
- Having a file which won't be pushed in this repo containing the API URL and other sensitive data
