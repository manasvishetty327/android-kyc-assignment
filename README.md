# Digital KYC

Digital KYC is an Android application built using **Kotlin** and **Jetpack Compose** that simulates a customer KYC verification workflow for a digital banking platform.

The application allows relationship managers to browse customer accounts, view customer KYC profiles, complete KYC verification by capturing selfies, and resolve bank branch details live using IFSC codes.

This project follows **Clean Architecture** with proper separation of UI, Domain, and Data layers.

<p align="center">
  <img width="280" alt="Digital KYC Screenshot" src="https://github.com/user-attachments/assets/218954ad-286d-419b-9b5f-e006752782f1" />
</p>

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

| Category | Technologies |
|----------|-------------|
| **Language** | Kotlin |
| **UI** | Jetpack Compose, Material 3 |
| **Architecture** | MVVM, Clean Architecture |
| **Dependency Injection** | Hilt |
| **Networking** | Retrofit |
| **Image Loading** | Coil |
| **Local Storage** | SharedPreferences, Gson |
| **Camera** | CameraX |
| **State Management** | StateFlow, MutableStateFlow |
| **Navigation** | Jetpack Navigation Compose |

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
[https://dummyjson.com/users](https://dummyjson.com/users)

Used for:
- Customer profile details
- Bank account details
- Profile image
- Contact information

---

### IFSC Verification API
**Razorpay IFSC API**  
[https://ifsc.razorpay.com/](https://ifsc.razorpay.com/)

Used for:
- Live bank name resolution
- Branch resolution
- State verification

---
## Splash Screen

The application starts with a simple splash screen displaying the Digital KYC branding before loading customer data.

<p align="center">
  <img width="280" alt="Splash Screen" src="https://github.com/user-attachments/assets/178990f7-3d1f-4c6d-ba68-20f91a3cec8f" />
</p>

---
## Screenshots

### Home Screens

**Light Mode** | **Dark Mode**

Here, you can find the masked account number ending with 5865 (***5865).

<p align="center">
  <img width="280" alt="homescreen-light" src="https://github.com/user-attachments/assets/869730ae-a991-4ecc-8324-3ca65300678a" />
  <img width="280" alt="homescreen-dark" src="https://github.com/user-attachments/assets/e294dabc-dc0d-4d2b-8187-bc5a76586a9c" />
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

<p align="center">
  <img width="280" alt="Camera Permission" src="https://github.com/user-attachments/assets/fc1357a7-3175-46a7-bef0-7ea8c6309500" />
</p>

---

### Selfie Capture

**Tested on Device**

In-app camera preview used for capturing customer selfie during KYC verification.

<p align="center">
  <img width="280" alt="Selfie Capture" src="https://github.com/user-attachments/assets/3d7aaf89-a3ec-4764-87fb-f321b89009c0" />
</p>

### Verified Customer Flow   


Example customer: **Michael Williams**

#### 1. Customer in Pending List

Shows the customer before KYC verification in the Pending tab.

<p align="center">
  <img width="260" alt="Pending Customer" src="https://github.com/user-attachments/assets/efe6c613-f255-49a9-89cb-fdf27efbfe2c" />
</p>

---

#### 2. Customer Details (Pending Status)
Customer details screen before verification.

<p align="center">
  <img width="260" alt="Pending Details" src="https://github.com/user-attachments/assets/252a6dba-275f-42bc-9b29-41d5af44d306" />
</p>

---

#### 3. Verified Tab Initially Empty
Before verification, the Verified section has no customers.

<p align="center">
  <img width="260" alt="Empty Verified List" src="https://github.com/user-attachments/assets/a587c346-271d-4b55-a1d4-34ff020bacea" />
</p>

---

#### 4. Camera Opens for Selfie Capture
After clicking **Do KYC**, the in-app front camera opens.

<p align="center">
  <img width="260" alt="Camera Capture Screen" src="https://github.com/user-attachments/assets/5b4738fd-99c4-460e-99ab-3ad7ad4288a7" />
</p>

---

#### 5. Selfie Captured and Profile Updated
Captured selfie replaces the customer profile image and KYC action changes to **Retake Selfie**.

<p align="center">
  <img width="260" alt="After Capture Profile Updated" src="https://github.com/user-attachments/assets/748f8115-6d9a-49f8-bed4-85d4b1072f71" />
</p>

---

#### 6. Customer Moved to Verified Section
After successful KYC, customer is visible in the Verified tab.

<p align="center">
  <img width="260" alt="Verified Section" src="https://github.com/user-attachments/assets/202e4937-77b6-42ce-b6d3-027ff1cf9d6a" />
</p>

---

#### 7. Customer Details (Verified Status)
Customer details screen after verification.

<p align="center">
  <img width="260" alt="Verified Details" src="https://github.com/user-attachments/assets/843c943f-dea8-43b0-903a-43b6ce86b879" />
</p>

---

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
Developed by **Manasvi Shetty**
