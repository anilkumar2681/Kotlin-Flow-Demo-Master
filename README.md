# Kotlin-Flow-Demo-Master
![CoverImage](images/cover_image.png)

## 📌 Overview
This is a demo Android project showing **best practices for data loading** using:
- ViewModel
- Kotlin Flow
- Repository Pattern
- Hilt Dependency Injection
- Room Database
- Retrofit API
- Jetpack Compose UI

## 🚀 Usage
1. Clone the repository:
   ```bash
   git clone https://github.com/anilkumar2681/Kotlin-Flow-Demo-Master.git
   ```

2. Open in **Android Studio** (Arctic Fox or newer).

3. Run on emulator/device.

The app will fetch users from `https://json-placeholder.mock.beeceptor.com/users`, cache them into Room DB, and display them with Jetpack Compose.

## 🏗️ Architecture
```
UI (Jetpack Compose) <-> ViewModel <-> Repository <-> (Room DB + Retrofit API)
```

![Architecture Diagram](images/architecture.png)

## 📸 Screenshots
| Loading State                  | Success State | Error State |
|--------------------------------|---------------|-------------|
| ![Loading](images/loading.png) | ![Success](images/success.png) | ![Error](images/error.png) |

## 🔑 Key Concepts
- `UiState` for handling **Loading/Success/Error**
- `StateFlow` in ViewModel for UI state exposure
- Repository pattern for clean separation
- Offline-first approach (cache → network)

--- 
Made with ❤️ using Kotlin + Jetpack Compose
