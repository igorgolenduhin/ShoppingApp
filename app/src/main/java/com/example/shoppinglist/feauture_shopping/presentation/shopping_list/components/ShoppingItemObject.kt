package com.example.shoppinglist.feauture_shopping.presentation.shopping_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem

@Composable
fun ShoppingItemObject(
    shoppingItem: ShoppingItem,
    modifier: Modifier,
    onDeleteClick: () -> Unit,
    onCompleteClick: () -> Unit
) {
    Box(modifier = modifier.background(Color(shoppingItem.color))) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .border(
                        width = 3.dp,
                        color = if (shoppingItem.completion) {
                            Color.Green
                        } else Color.Red,
                        shape = CircleShape
                    )
                    .clickable {
                        onCompleteClick()
                    }
                // TODO: 11/12/2021 Make it in vertical center if we have more than 1 line of title

            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = shoppingItem.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(onClick = onDeleteClick) {
                Image(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }

        }
    }
}