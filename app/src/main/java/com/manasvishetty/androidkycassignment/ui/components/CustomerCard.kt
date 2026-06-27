package com.manasvishetty.androidkycassignment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import com.manasvishetty.androidkycassignment.data.model.Customer

@Composable
fun CustomerCard(
    customer: Customer,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = customer.image,
                contentDescription = customer.firstName,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
            ) {
                Text(
                    text = "${customer.firstName} ${customer.lastName}",
                    fontSize = 18.sp
                )

                Text(
                    text = "****${customer.bank.iban.takeLast(4)}"
                )

                Text(
                    text = "₹${String.format("%.2f", customer.balance)}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor =
                            if (customer.isVerified)
                                Color(0xFFDFF5E1)
                            else
                                Color(0xFFFFF3CD)
                    )
                ) {
                    Text(
                        text = if (customer.isVerified) "Verified" else "Pending",
                        color = Color.Black,
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        )
                    )
                }
            }
        }
    }
}