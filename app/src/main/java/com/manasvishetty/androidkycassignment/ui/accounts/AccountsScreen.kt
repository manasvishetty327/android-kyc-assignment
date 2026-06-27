package com.manasvishetty.androidkycassignment.ui.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.manasvishetty.androidkycassignment.ui.components.CustomerCard
import kotlinx.coroutines.delay
import kotlin.math.ceil
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun AccountsScreen(
    navController: NavController,
    viewModel: AccountsViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit
) {
    val customers by viewModel.customers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()

    val pageSize = viewModel.getPageSize()

    val onSurface = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
    val primary = MaterialTheme.colorScheme.primary

    var selectedTab by remember { mutableStateOf(0) }
    var selectedChip by remember { mutableStateOf("All") }

    var searchQuery by remember { mutableStateOf("") }
    var debouncedSearchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        delay(300)
        debouncedSearchQuery = searchQuery
    }

    val filteredCustomers = customers
        .filter {
            if (selectedTab == 0) !it.isVerified else it.isVerified
        }
        .filter {
            when (selectedChip) {
                "Savings" -> it.accountType == "Savings"
                "Current" -> it.accountType == "Current"
                "NRI" -> it.accountType == "NRI"
                else -> true
            }
        }
        .filter {
            it.firstName.contains(debouncedSearchQuery, true) ||
                    it.lastName.contains(debouncedSearchQuery, true) ||
                    it.bank.iban.contains(debouncedSearchQuery, true)
        }

    val totalPages =
        ceil(filteredCustomers.size / pageSize.toDouble()).toInt()

    val paginatedCustomers =
        filteredCustomers
            .drop((currentPage - 1) * pageSize)
            .take(pageSize)

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.padding(24.dp)
            )
        }
        return
    }

    if (error != null) {
        Text(
            text = error!!,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.error
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Digital KYC",
                style = MaterialTheme.typography.titleLarge,
                color = onSurface
            )

            IconButton(onClick = onThemeToggle) {
                Text(
                    text = if (isDarkMode) "☀" else "🌙"
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = {
                    Text(
                        "Pending",
                        color = if (selectedTab == 0) primary else onSurface
                    )
                }
            )

            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = {
                    Text(
                        "Verified",
                        color = if (selectedTab == 1) primary else onSurface
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = {
                Text("Search by name or account number")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = onSurface,
                unfocusedTextColor = onSurface,
                focusedLabelColor = onSurfaceVariant,
                unfocusedLabelColor = onSurfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "Savings", "Current", "NRI").forEach { chip ->
                FilterChip(
                    selected = selectedChip == chip,
                    onClick = { selectedChip = chip },
                    label = {
                        Text(
                            chip,
                            color = if (selectedChip == chip)
                                MaterialTheme.colorScheme.onPrimaryContainer
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (selectedTab == 0)
                "Pending Customers"
            else
                "Verified Customers",
            style = MaterialTheme.typography.titleMedium,
            color = onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(paginatedCustomers) { customer ->
                CustomerCard(
                    customer = customer,
                    onClick = {
                        navController.navigate("details/${customer.id}")
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.previousPage() },
                enabled = currentPage > 1
            ) {
                Text("Previous")
            }

            Text(
                text = "Page $currentPage of $totalPages",
                style = MaterialTheme.typography.bodyLarge,
                color = onSurface
            )

            Button(
                onClick = {
                    viewModel.nextPage(filteredCustomers.size)
                },
                enabled = currentPage < totalPages
            ) {
                Text("Next")
            }
        }
    }
}