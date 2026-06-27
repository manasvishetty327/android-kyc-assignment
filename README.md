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

## Home Screens

**Light Mode** | **Dark Mode**

<p align="center">
  <img width="280" alt="Home Light" src="LIGHT_IMAGE_URL" />
  <img width="280" alt="Home Dark" src="DARK_IMAGE_URL" />
</p>


### Filtered Accounts

**All** | **Savings** | **Current** | **NRI**

<p align="center">
  <img width="250" alt="All Accounts" src="https://github.com/user-attachments/assets/b2f6e32d-00b1-415a-a952-b7ae51268b5f" />
  <img width="250" alt="Savings Accounts" src="https://github.com/user-attachments/assets/7ae23dcd-cfa9-4de3-bae3-d2ab9274633c" />
  <img width="250" alt="Current Accounts" src="https://github.com/user-attachments/assets/bdace38b-ccdd-4fb3-8a59-adef05d479c4" />
</p>

<p align="center">
  <img width="250" alt="NRI Accounts" src="https://github.com/user-attachments/assets/d0af159a-2181-4961-bcd8-1a2022153dba" />
</p>

### Pagination

**Page 1** | **Page 2** | **Page 3**

<p align="center">
  <img width="250" alt="Page 1" src="https://github.com/user-attachments/assets/1f49bceb-e8b9-48db-b1e7-1cb9a91198a0" />
  <img width="250" alt="Page 2" src="https://github.com/user-attachments/assets/675c337b-a576-4aa9-9698-f4c593c0763f" />
  <img width="250" alt="Page 3" src="https://github.com/user-attachments/assets/3fa65b21-1772-474a-9925-a9b9fc2e2af9" />
</p>

### Customer Details

**Light Mode** | **Dark Mode**

<p align="center">
  <img width="280" alt="Details Light" src="https://github.com/user-attachments/assets/eaf3a74a-f4d1-4fd7-93fd-4b77db8dd5c8" />
  <img width="280" alt="Details Dark" src="https://github.com/user-attachments/assets/154d2d13-05eb-4df4-89db-ba0fd0680ae9" />
</p>

### Camera Permission
<img width="1080" height="2408" alt="image" src="https://github.com/user-attachments/assets/fc1357a7-3175-46a7-bef0-7ea8c6309500" />


### Selfie Capture
<img width="1080" height="2408" alt="image" src="https://github.com/user-attachments/assets/82ede848-7616-48ce-a173-bd2941bfbc41" />


### Verified Customer Flow


---

## Future Enhancements

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
