# 📦 Bosta Delivery API Integration Task

This project demonstrates integration with Bosta's Delivery API to fetch and display a list of cities and their districts. Built using **MVVM architecture**, **Jetpack Compose**, **Hilt**, and **Retrofit**.

---

## 🛠 Tech Stack

- **Kotlin**
- **MVVM Architecture**
- **Retrofit**
- **Jetpack Compose**
- **Hilt (Dependency Injection)**
- **Kotlin Coroutines**
- **StateFlow / LiveData**

---

## Features

- Fetch cities and their districts from Bosta API
-  Display items in a list (RecyclerView or Compose list)
-  Handle UI states: Loading, Success, Error
-  Use proper architecture: Repository, UseCase, ViewModel, DI
-  Modular and testable codebase

---

## 📁 Project Structure
📂 app
┣ 📂 data
┃ ┣ 📂 model # Data models (City, District, etc.)
┃ ┣ 📂 remote # Retrofit API interface
┃ ┗ 📂 repository # Manages data flow from API
┣ 📂 domain
┃ ┗ 📂 usecase # Business logic layer
┣ 📂 presentation
┃ ┣ 📂 ui # Jetpack Compose UI components
┃ ┗ 📂 viewmodel # Handles state and logic
┣ 📂 di # Hilt modules for dependency injection
┗ 📜 MainActivity.kt

## 🚧 Implementation Steps 

### 1. ✅ Setup Hilt for Dependency Injection
- Add Hilt plugin & dependencies in `build.gradle.kts`
- Create `@HiltAndroidApp` application class
- Annotate ViewModels with `@HiltViewModel`
- Provide UseCase and Repository via `@Module`

### 2. ✅ Define Data Models
- `City`, `District` for API response

### 3. ✅ Retrofit Integration
- Create Retrofit interface: `ApiService`
- Base URL: `http://app.bosta.co/api/v2/`
- Add authorization header using interceptor or manually in each request

### 4. ✅ Repository Implementation
- Create `Repository` interface + `RepositoryImpl`
- Handle API call with coroutine
- Map DTO to domain model if needed

### 5. ✅ UseCase
- Create a `GetCityhDistrictsUseCase` that invokes repository method
- Encapsulates business logic

### 6. ✅ ViewModel
- Expose `StateFlow` or `LiveData` to UI
- Emit `Loading`, `Success`, `Error` states using sealed class `UiState`

### 7. ✅ UI Layer
- Create a composable view `CityDistrictsScreen`
- Collect data from `viewModel`
- Use `LazyColumn` to show data
- Handle empty state, errors, and loading

### 8. ⏳ Error Handling
- Show proper error messages in UI
- Handle timeout, network, and 401 errors

### 9. ⏳ Testing (Optional)
- Unit test for ViewModel, UseCase
- Mock API using FakeRepository

---
## Installation

To run the Food Planner Java project locally, follow these steps:

1. Clone this repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.
---

## 📸 Screenshots
![Delivery Screen]

https://private-user-images.githubusercontent.com/115953332/432929438-2ac8fa97-2acc-4c32-bbc6-779bf0a8e78b.jpeg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ0MDMyNTcsIm5iZiI6MTc0NDQwMjk1NywicGF0aCI6Ii8xMTU5NTMzMzIvNDMyOTI5NDM4LTJhYzhmYTk3LTJhY2MtNGMzMi1iYmM2LTc3OWJmMGE4ZTc4Yi5qcGVnP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTFUMjAyMjM3WiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9MzZkY2UxMTRiNzE2MTNjZjM0ODQ4MmU3ZmVhZWJjNjljNjE2MGI2ZjZlNjY3MjJhN2E5MDYwNTczMzYzMGYzZSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ.LDD6at72Op4yusHf6oJTVtN4QnTNw-mgaNwGmYL6Df8


https://private-user-images.githubusercontent.com/115953332/432929438-2ac8fa97-2acc-4c32-bbc6-779bf0a8e78b.jpeg?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ0MDMxMzMsIm5iZiI6MTc0NDQwMjgzMywicGF0aCI6Ii8xMTU5NTMzMzIvNDMyOTI5NDM4LTJhYzhmYTk3LTJhY2MtNGMzMi1iYmM2LTc3OWJmMGE4ZTc4Yi5qcGVnP1gtQW16LUFsZ29yaXRobT1BV1M0LUhNQUMtU0hBMjU2JlgtQW16LUNyZWRlbnRpYWw9QUtJQVZDT0RZTFNBNTNQUUs0WkElMkYyMDI1MDQxMSUyRnVzLWVhc3QtMSUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LURhdGU9MjAyNTA0MTFUMjAyMDMzWiZYLUFtei1FeHBpcmVzPTMwMCZYLUFtei1TaWduYXR1cmU9OTExMzRkODEwZDJjYmU4OWJmNTIyMTIxMWM5YWUyZDA3YTU3M2M3OGVkYjc3ODBmODI0NjE1OTI5NTRmZTVjOSZYLUFtei1TaWduZWRIZWFkZXJzPWhvc3QifQ._8w-FcNrTl_SVEusO3uC-b_fSFFw5Jrhdg-CsRBElg4
