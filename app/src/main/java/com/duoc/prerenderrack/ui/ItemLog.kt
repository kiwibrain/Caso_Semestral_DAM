package com.duoc.prerenderrack.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemLog(fecha: String, rack:String, descripcion:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF6FF))
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            //fecha
            Text(
                text = fecha,
                color = Color(0xFF1E2749),
                fontSize = 13.sp
            )
            //division
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 1.dp,
                color = Color(0xFFB4C8E6)
            )
            //N° rack y descripcion
            Text(
                text = rack,
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = descripcion,
                color = Color.Black,
                fontSize = 13.sp
            )

        }
    }
}