# Movie Discovery App

## Implementation Notes
- Design a clean and responsive user interface with MVVM and clean architecture.
- Use Kotlin Coroutines and Retrofit for network operations.
- Implement dependency injection with Hilt to manage dependencies.
- Ensure proper state management using flow APIs to handle loading, success, and error states.
- Use Room for local data storage.
- GitHub repository with the complete source code.
- UI test is a plus.

### Application contains of the following screens
- Main screen with two navigation tabs: "Home" and "Watchlist”.
- Movie Details screen.
  
## App Functionality

### Main Screen (Home Tab):
- Fetch the top 10 popular movies, display these movies horizontally.
- Use pagination to fetch and display 2024 movies, grouped by their release month into separated sections.
- Each movies group must have its month as a header.
- Fetch and display the required APIs data sequentially.
- Use cache-first approach to fetch movies data locally in case of connection issues.

### Main Screen (Watchlist Tab):
- Use Room database to retrieve saved movies.
- Allow users to un-save movies from the watchlist.

### Details Screen:
- Allow users to navigate to the movie details for each movie item.
- In parallel, fetch details, acting cast and the first page of similar movies for the selected movie.
- Ensure that a single API failure doesn't prevent the user from seeing other fetched data.
- Update the UI to display movie poster image, movie title, genres,
- list of cast members and a list of similar movies simultaneously.
- Using Room, allow users to update their watching list by saving/unsaving the movie.

## Project Modules:
### app module
The app module is the entry point of the application. It ties together all other modules, handling the application's activities. 
It depends on modules such as presentation, di, util, and core to build and initialize its components.

```sh
implementation(project(":presentation")) // app depends on presentation for adding views in activities
implementation(project(":di")) // app depends on di to init modules in application
implementation(project(":util")) // app depends on util
implementation(project(":core")) // app depends on core
```


### presentation module
The presentation module is responsible for the user interface and user experience aspects of the application. 
It includes ViewModels and UI-related logic. 
This module depends on domain for ViewModel data and util and core for utility functions and core functionality.

```sh
implementation(project(":domain")) // presentation depends on domain for ViewModels
implementation(project(":util")) // presentation depends on util
implementation(project(":core")) // presentation depends on core
```


### core module
The core module provides fundamental functionalities and common utilities used across various modules. 
This module contains the base classes and core components that other modules depend on.
Also contain all project resources that will be used across the application in all other modules.
The core module doesn't depend on any other modules.


### di module
The di (Dependency Injection) module manages dependency injection across the application. 
It sets up and provides dependencies required by other modules, such as data, domain, util, and core.

```sh
implementation(project(":data")) // di depends on data for repository implementations
implementation(project(":domain")) // di depends on domain for repository interfaces
implementation(project(":util")) // di depends on util
implementation(project(":core")) // di depends on core
```


### domain module
The domain module contains the business logic and domain models of the application. 
It defines the core interfaces and data structures used by other modules, relying on util and core for essential utilities and functionalities.

```sh
implementation(project(":util")) // domain depends on util
implementation(project(":core")) // domain depends on core
```


### data module
The data module handles data management and repository implementations. 
It provides the concrete implementations for the interfaces defined in the domain module and uses util and core for additional functionality.

```sh
implementation(project(":domain")) // data depends on domain for interfaces
implementation(project(":util")) // data depends on util
implementation(project(":core")) // data depends on core
```


### utill module
The util module offers utility functions and helper classes that are used throughout the application. 
It is a foundational module that other modules rely on for common operations and functionality.

```sh
implementation(project(":core")) // Util depends on core
```

## Getting Started

### Prerequisites
- Android Studio
- Movies Discovery App api key

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/fady0bassem/Movie-Discovery-App.git
2. Open the project in Android Studio.
3. Add your apikey:
   ```sh
   com.fadybassem.util.AppConfiguration.API_KEY
4. Sync your project with Gradle files and build the project.

### Usage
- Run the application on an Android device or emulator.

## APK
[Click here to download apk](https://github.com/fady0bassem/Movie-Discovery-App/blob/main/apk/app-debug.apk)

## Screenshots

<div style="display: flex; flex-wrap: wrap; gap: 5px;"> 
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/1.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/2.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
   <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/3.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/4.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
</div>

<div style="display: flex; flex-wrap: wrap; gap: 5px;"> 
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/5.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/6.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
   <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/7.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/8.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
</div>

<div style="display: flex; flex-wrap: wrap; gap: 5px;"> 
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/9.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/10.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
   <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/11.png" width="200" alt="Screenshot 4" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
  <img src="https://github.com/fady0bassem/Movie-Discovery-App/blob/main/screenshots/12.png" width="200" alt="Screenshot 5" style="flex: 1 1 calc(33.333% - 10px); max-width: 100%;">
</div>
