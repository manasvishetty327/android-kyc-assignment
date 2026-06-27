# Digital KYC

Digital KYC is an Android application built using **Kotlin** and **Jetpack Compose** that simulates a customer KYC verification workflow for a digital banking platform.

The application allows relationship managers to browse customer accounts, view customer KYC profiles, complete KYC verification by capturing selfies, and resolve bank branch details live using IFSC codes.

This project follows **Clean Architecture** with proper separation of UI, Domain, and Data layers.

---

## Features

### Accounts Screen
- Browse customer accounts in **Pending** and **Verified** tabs
- Search customers by:
  - Name
  - Account Number (IBAN)
- Filter customers using category chips:
  - All
  - Savings
  - Current
  - NRI
- Paginated customer listing
- Dynamic Light/Dark mode switching

---

### Customer Details Screen
Displays:
- Customer profile photo
- Full name
- Masked account number
- Account balance
- Date of birth
- Phone number
- Email
- Address
- Nationality
- IFSC code
- Live bank and branch details

---

### KYC Verification
- In-app selfie capture using **CameraX**
- Runtime camera permission handling
- Updates customer KYC status from Pending to Verified
- Moves verified customer to Verified tab
- Stores captured selfie locally and displays it as customer photo

---

### Performance Optimizations
- API response caching with expiration (1 hour)
- Debounced search
- Lazy image loading using Coil
- Pagination for customer listing
- Local persistence using SharedPreferences

---

## Tech Stack

### Language
- Kotlin

### UI
- Jetpack Compose
- Material 3

### Architecture
- MVVM
- Clean Architecture

### Dependency Injection
- Hilt

### Networking
- Retrofit

### Image Loading
- Coil

### Local Storage
- SharedPreferences
- Gson

### Camera
- CameraX

---

## Project Structure

```text
app/
├── data/
│   ├── local/
│   │   ├── CustomerCache.kt
│   │   ├── KycStorage.kt
│   │   └── ThemePreference.kt
│   │
│   ├── model/
│   │   ├── Address.kt
│   │   ├── Bank.kt
│   │   ├── Customer.kt
│   │   ├── IfscResponse.kt
│   │   └── UserResponse.kt
│   │
│   ├── remote/
│   │   ├── ApiService.kt
│   │   ├── RetrofitInstance.kt
│   │   └── IfscRetrofitInstance.kt
│   │
│   └── repository/
│       └── CustomerRepository.kt
│
├── domain/
│   ├── repository/
│   │   └── CustomerRepositoryInterface.kt
│   │
│   └── usecase/
│       └── GetCustomersUseCase.kt
│
├── di/
│   └── AppModule.kt
│
├── ui/
│   ├── accounts/
│   │   ├── AccountsScreen.kt
│   │   └── AccountsViewModel.kt
│   │
│   ├── details/
│   │   └── DetailsScreen.kt
│   │
│   ├── camera/
│   │   └── CameraScreen.kt
│   │
│   ├── components/
│   │   └── CustomerCard.kt
│   │
│   ├── navigation/
│   │   └── AppNavigation.kt
│   │
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── MainActivity.kt
└── MyApp.kt
```

---

## APIs Used

### Customer Data API
**DummyJSON**  
https://dummyjson.com/users

Used for:
- Customer profile details
- Bank account details
- Profile image
- Contact information

---

### IFSC Verification API
**Razorpay IFSC API**  
https://ifsc.razorpay.com/

Used for:
- Live bank name resolution
- Branch resolution
- State verification

---

## Screenshots

### Home Screen (Light Mode)
<img width="311" height="631" alt="homescreen-light" src="https://github.com/user-attachments/assets/869730ae-a991-4ecc-8324-3ca65300678a" />


### Home Screen (Dark Mode)
<img width="301" height="641" alt="homescreen-dark" src="https://github.com/user-attachments/assets/e294dabc-dc0d-4d2b-8187-bc5a76586a9c" />

## Home Screens
<p align="center">
  <img width="280" alt="homescreen-light" src="https://github.com/user-attachments/assets/869730ae-a991-4ecc-8324-3ca65300678a" />
  <img width="280" alt="homescreen-dark" src="https://github.com/user-attachments/assets/e294dabc-dc0d-4d2b-8187-bc5a76586a9c" />
</p>


### Filtered Accounts
<p align="center">
  <img src="screenshots/home-light.png" width="250"/>
  <img src="screenshots/home-dark.png" width="250"/>
</p>

### Pagination
(Add screenshot here)

### Customer Details (Light Mode)
(Add screenshot here)

### Customer Details (Dark Mode)
(Add screenshot here)

### Camera Permission
(Add screenshot here)

### Selfie Capture
(Add screenshot here)

### Verified Customer Flow
(Add screenshot here)

---

## Future Enhancements

## Future Improvements

There are a few areas where this application can be extended further:

- Sync KYC verification data with a backend so customer verification status remains available even after app reinstall or across devices.
- Store captured customer selfies in cloud storage instead of only local device storage.
- Add OCR-based document scanning to automate customer identity verification.
- Improve pagination with infinite scrolling for a smoother browsing experience.
- Introduce biometric-based verification for stronger KYC validation.
- Expand the `utils` layer for reusable helpers such as account masking, data formatting, and common validation utilities to keep the codebase cleaner and more scalable.

---

## Author

**Manasvi Shetty**
