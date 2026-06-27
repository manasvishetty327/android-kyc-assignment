package com.manasvishetty.androidkycassignment.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.manasvishetty.androidkycassignment.data.model.IfscResponse
import com.manasvishetty.androidkycassignment.data.remote.IfscRetrofitInstance
import com.manasvishetty.androidkycassignment.ui.accounts.AccountsViewModel

@Composable
fun DetailsScreen(
    customerId: Int,
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val customers by viewModel.customers.collectAsState()
    val customer = customers.find { it.id == customerId }

    val onSurface = MaterialTheme.colorScheme.onSurface

    var bankDetails by remember {
        mutableStateOf<IfscResponse?>(null)
    }

    var isBankLoading by remember {
        mutableStateOf(true)
    }

    var bankError by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(customer?.ifsc) {
        customer?.ifsc?.let { ifsc ->
            try {
                isBankLoading = true
                bankError = null

                bankDetails =
                    IfscRetrofitInstance.api.getBankDetails(ifsc)

                isBankLoading = false

            } catch (e: Exception) {
                bankError = "Failed to fetch bank details"
                isBankLoading = false
            }
        }
    }

    customer?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .statusBarsPadding()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = it.image,
                    contentDescription = it.firstName,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${it.firstName} ${it.lastName}",
                        style = MaterialTheme.typography.titleLarge,
                        color = onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "A/C: ****${it.bank.iban.takeLast(4)}",
                        color = onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "₹${String.format("%.2f", it.balance)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Personal Details",
                style = MaterialTheme.typography.titleMedium,
                color = onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Phone: ${it.phone}", color = onSurface)
            Text("Email: ${it.email}", color = onSurface)
            Text("DOB: ${it.birthDate}", color = onSurface)
            Text("Nationality: ${it.address.country}", color = onSurface)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Bank Details",
                style = MaterialTheme.typography.titleMedium,
                color = onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Address: ${it.address.address}, ${it.address.city}",
                color = onSurface
            )
            Text(
                "IBAN: ${it.bank.iban}",
                color = onSurface
            )
            Text(
                "IFSC: ${it.ifsc}",
                color = onSurface
            )

            if (isBankLoading) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Resolving bank details...",
                    color = onSurface
                )
            }

            if (bankError != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    bankError!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            bankDetails?.let { bank ->
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Bank: ${bank.BANK}",
                    color = onSurface
                )
                Text(
                    "Branch: ${bank.BRANCH}",
                    color = onSurface
                )
                Text(
                    "State: ${bank.STATE}",
                    color = onSurface
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "KYC Status: ${
                    if (it.isVerified) "Verified" else "Pending"
                }",
                style = MaterialTheme.typography.titleMedium,
                color = onSurface
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    navController.navigate("camera/$customerId")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (it.isVerified)
                        "Retake Selfie"
                    else
                        "Do KYC"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}